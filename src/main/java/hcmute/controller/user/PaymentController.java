package hcmute.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.embeddedId.OrderDetailId;
import hcmute.entity.*;
import hcmute.model.MilkTeaModel;
import hcmute.model.OrderData;
import hcmute.model.OrderProduct;
import hcmute.model.OrderProduct.OrderItem;
import hcmute.service.*;
import hcmute.service.impl.CookieServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class PaymentController {
    IOrderService orderService;
    IOrderDetailService orderDetailService;
    IUserService userService;
    IPayMethodService payMethodService;
    IMilkTeaService milkTeaService;
    IBranchService branchService;
    CookieServiceImpl cookieServiceImpl;

    @GetMapping("")
    private String displayPayment(
            ModelMap model, @RequestParam("data") String data, @RequestParam("listBranch") String listBranch
    )
            throws UnsupportedEncodingException {
//		data = URLDecoder.decode(data, "UTF-8");
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        data = new String(decodedBytes, StandardCharsets.UTF_8);
        model.addAttribute("dataJSON", data);
        List<PayMethodEntity> listPayMethod = payMethodService.findAll();
        model.addAttribute("listPayMethod", listPayMethod);
        int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
        Optional<UserEntity> optCustomer = userService.findById(idUser);
        if (optCustomer.isPresent()) {
            UserEntity customer = optCustomer.get();
            model.addAttribute("customer", customer);
        }

        byte[] branchBytes = Base64.getDecoder().decode(listBranch);
        String branchString = new String(branchBytes);
        branchString = branchString.trim();
        if (branchString.startsWith("[")) {
            branchString = branchString.substring(1, branchString.length() - 1);
        }

        List<String> branchList = new ArrayList<>(Arrays.asList(branchString.split(",")));
        List<BranchEntity> branches = new ArrayList<>();
        for (String branchId : branchList) {
            int id = Integer.parseInt(branchId);
            Optional<BranchEntity> opt = branchService.findById(id);
            opt.ifPresent(branches::add);
        }
        model.addAttribute("branches", branches);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            OrderProduct orderProduct = objectMapper.readValue(data, OrderProduct.class);
            List<MilkTeaModel> listMilkTea = new ArrayList<>();
            for (OrderItem item : orderProduct.getList()) {
                Optional<MilkTeaEntity> entity = milkTeaService.findByIdMilkTea(Integer.parseInt(item.getIdMilkTea()));
                if (entity.isPresent()) {
                    MilkTeaModel milkTeaModel = new MilkTeaModel();
                    BeanUtils.copyProperties(entity.get(), milkTeaModel);
                    milkTeaModel.setSize(item.getSize());
                    milkTeaModel.setOrderQuantity(Integer.parseInt(item.getQuantity()));
                    milkTeaModel.setCost(item.getPrice());
                    listMilkTea.add(milkTeaModel);
                }
            }
            model.addAttribute("orderProduct", orderProduct);
            model.addAttribute("listMilkTea", listMilkTea);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "user/payment";
    }

    @GetMapping("/order")
    private String insertOrder(ModelMap model, @RequestParam("data") String data)
            throws UnsupportedEncodingException, JsonProcessingException {
        int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        data = new String(decodedBytes, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            OrderData orderData = objectMapper.readValue(data, OrderData.class);
            Order order = new Order();
            order.setTotalProduct(orderData.getTotalProduct());
            order.setTotalPrice(orderData.getTotalPrice());
            order.setFinalPrice(orderData.getFinalPrice());
            LocalDateTime localDateTime = LocalDateTime.parse(orderData.getOrderDay(), formatter);
            order.setOrderDay(localDateTime.truncatedTo(ChronoUnit.SECONDS));
            order.setOrderState(orderData.getOrderState());
            localDateTime = LocalDateTime.parse(orderData.getShipDay(), formatter);
            order.setShipDay(localDateTime.truncatedTo(ChronoUnit.SECONDS));
            order.setNote(orderData.getNote());
            order.setAddress(orderData.getAddress());
            order.setPhoneNumber(orderData.getPhoneNumber());
            order.setFee(orderData.getFee());
            Optional<BranchEntity> optBranch = branchService.findById(orderData.getIdBranch());
            optBranch.ifPresent(order::setBranchByOrder);

            if (orderData.getIdPayMethod() != null) {
                Optional<PayMethodEntity> payMethodOpt = payMethodService.findById(orderData.getIdPayMethod());
                payMethodOpt.ifPresent(order::setPayMethodByOrder);
            }

            Optional<UserEntity> optCustomer = userService.findById(idUser);
            optCustomer.ifPresent(order::setCustomerByOrder);
            orderService.save(order);

            for (OrderData.OrderItem item : orderData.getList()) {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setQuantity(item.getQuantity());
//				orderDetailEntity.setCurrPrice(item.getPrice());

                Optional<MilkTeaEntity> milkTeaEntity = milkTeaService.findByIdMilkTea(item.getIdMilkTea());

                milkTeaEntity.ifPresent(orderDetailEntity::setMilkTeaByOrderDetail);

                orderDetailEntity.setOrderByOrderDetail(order);

                OrderDetailId idOrderDetail = new OrderDetailId();
                idOrderDetail.setSize(item.getSize());
                idOrderDetail.setIdOrder(orderDetailEntity.getOrderByOrderDetail().getIdOrder());
                idOrderDetail.setIdMilkTea(orderDetailEntity.getMilkTeaByOrderDetail().getIdMilkTea());
                orderDetailEntity.setIdOrderDetail(idOrderDetail);

                orderDetailService.save(orderDetailEntity);
                model.addAttribute("orderMessage", "Bạn đã đặt hàng thành công!");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("orderMessage", "Đặt hàng thất bại!");
        }
        return "user/home";
    }
}