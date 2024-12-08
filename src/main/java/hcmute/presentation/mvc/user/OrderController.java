package hcmute.presentation.mvc.user;

import hcmute.entity.BranchEntity;
import hcmute.entity.Order;
import hcmute.service.IBranchService;
import hcmute.service.ICartService;
import hcmute.service.IOrderDetailService;
import hcmute.service.IOrderService;
import hcmute.service.impl.CookieServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OrderController {
    IOrderService orderService;
    IOrderDetailService orderDetailService;
    IBranchService branchService;
    ICartService cartService;
    CookieServiceImpl cookieServiceImpl;

    // Hiển thị tất cả các đơn hàng
    @RequestMapping("")
    public String showAllOrders(ModelMap model) {
        try {
            int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
            List<Order> list = orderService.findAllOrdersByUserId(idUser);
            model.addAttribute("orders", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/order";
    }

    // Hiển thị chi tiết đơn hàng
    @RequestMapping("order-detail/{idOrder}")
    public String showOrderDetail(ModelMap model, @PathVariable("idOrder") Integer idOrder) {
        try {
            int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
            List<Order> list = orderService.findAllOrdersByUserId(idUser);
            Order userOrder = orderService.getById(idOrder);
            model.addAttribute("orders", list);
            model.addAttribute("userOrder", userOrder);
            model.addAttribute("idOrder", idOrder);
            BranchEntity entity = userOrder.getBranchByOrder();
            model.addAttribute("addressOrder", entity.getAddressDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/order";
    }

    // Hiển thị trạng thái đơn hàng của một ID đơn hàng cụ thể
    @RequestMapping("/status/{idOrder}")
    public String showOrderStatus(ModelMap model, @PathVariable("idOrder") Integer idOrder) {
        try {
            int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
            List<Order> list = orderService.findAllOrdersByUserId(idUser);
            Order userOrder = orderService.getById(idOrder);
            model.addAttribute("orders", list);
            model.addAttribute("userOrder", userOrder);
            model.addAttribute("idOrder", idOrder);
            BranchEntity entity = userOrder.getBranchByOrder();
            model.addAttribute("addressOrder", entity.getAddressDetail());
            return "user/order_status"; // Trang JSP để hiển thị trạng thái đơn hàng
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Trang lỗi nếu có sự cố
        }
    }

    // Hiển thị tất cả trạng thái của đơn hàng cho người dùng (không cần ID)
    @GetMapping("/status")
    public String showAllOrderStatus(ModelMap model) {
        try {
            int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
            List<Order> list = orderService.findAllOrdersByUserId(idUser);
            model.addAttribute("orders", list);

            return "user/all_order_status"; // Trang hiển thị tất cả trạng thái đơn hàng
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Trang lỗi nếu có sự cố
        }
    }
}

