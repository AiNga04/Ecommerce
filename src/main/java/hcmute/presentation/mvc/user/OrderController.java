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
import org.springframework.ui.ModelMap;
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

}
