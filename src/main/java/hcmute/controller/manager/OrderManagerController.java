package hcmute.controller.manager;

import hcmute.entity.BranchEntity;
import hcmute.entity.Order;
import hcmute.entity.UserEntity;
import hcmute.model.OrderModel;
import hcmute.service.IOrderService;
import hcmute.service.IUserService;
import hcmute.service.impl.CookieServiceImpl;
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
@RequestMapping("manager/order")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OrderManagerController {
    CookieServiceImpl cookieServiceImpl;
    IOrderService orderService;
    IUserService userService;

    @GetMapping("")
    public String getOrder(ModelMap model) {
        int userId = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
        Optional<UserEntity> opt = userService.findById(userId);
        if (opt.isPresent()) {
            UserEntity entity = opt.get();
            BranchEntity branch = entity.getBranchByUser();
            List<Order> listOrder = orderService.findByBranchByOrder(branch);
            model.addAttribute("listOrder", listOrder);
        }
        return "manager/view/view-user-order";
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
            return new ModelAndView("manager/customize/customize-user-order", model);
        }

        model.addAttribute("message", "UserOrder không tồn tại");
        return new ModelAndView("forward:/manager/order", model);
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
                orderService.save(entity);
            }
        } else {
            model.addAttribute("message", "Không thể lưu UserOrder với dữ liệu null");
        }

        return new ModelAndView("redirect:/manager/order", model);
    }
}
