package hcmute.presentation.mvc.admin;

import hcmute.dto.request.NotificationRequest;
import hcmute.service.impl.NotificationService;
import hcmute.entity.Order;
import hcmute.model.enums.Status;
import hcmute.model.OrderModel;
import hcmute.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/order")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserOrderAdminController {
    IOrderService orderService;
    NotificationService notificationService;

    @GetMapping("")
    public String IndexViewUserOrder(ModelMap model) {
        List<Order> listOrder = orderService.findAll();
        model.addAttribute("listOrder", listOrder);
        return "admin/view/view-user-order";
    }

    @GetMapping("edit/{userOrderId}")
    public ModelAndView edit(ModelMap model, @PathVariable("userOrderId") int userOrderId) {
        Optional<Order> opt = orderService.findById(userOrderId);
        OrderModel order = new OrderModel();
        if (opt.isPresent()) {
            Order entity = opt.get();
            BeanUtils.copyProperties(entity, order);
            order.setIsEdit(true);
            model.addAttribute("userOrder", order);
            return new ModelAndView("admin/customize/customize-user-order", model);
        }

        model.addAttribute("message", "UserOrder không tồn tại");
        return new ModelAndView("forward:/admin/order", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(
            ModelMap model, @Valid @ModelAttribute("userOrder") OrderModel userOrder,
            BindingResult result
    ) {
        if (userOrder != null) {
            Optional<Order> opt = orderService.findById(userOrder.getIdOrder());
            if (opt.isPresent()) {
                Order entity = opt.get();
                entity.setOrderState(userOrder.getOrderState());
                // implement websocket to send notification to user
                String userId = String.valueOf(entity.getCustomerByOrder().getId());
                notificationService.sendNotification(
                        userId,
                        NotificationRequest.builder()
                                           .title("Cập nhật trạng thái đơn hàng")
                                           .content("Đơn hàng " + entity.getIdOrder() + " đã được cập nhật")
                                           .orderId("Mã đơn hàng: " + entity.getIdOrder())
                                           .status(Status.ORDER)
                                           .build()
                );

                // save user order
                orderService.save(entity);
            }
        } else {
            model.addAttribute("message", "Không thể lưu UserOrder với dữ liệu null");
        }

        return new ModelAndView("redirect:/admin/order", model);
    }
}
