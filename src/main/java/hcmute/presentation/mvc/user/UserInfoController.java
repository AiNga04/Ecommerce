package hcmute.presentation.mvc.user;

import hcmute.entity.UserEntity;
import hcmute.model.UserModel;
import hcmute.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user_infor")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserInfoController {
    IUserService userService;

    @GetMapping("/{user_id}")
    public String viewuser(ModelMap model, @PathVariable("user_id") Integer userId) {
        Optional<UserEntity> opt = userService.findById(userId);

        if (opt.isPresent()) {
            UserEntity user = opt.get();
            model.addAttribute("user", user);
            return "user/user_infor";
        } else {
            model.addAttribute("message", "Không tìm thấy thông tin khách hàng với ID: " + userId);
            return "user/error";
        }
    }

    @PostMapping("/edit/{user_id}")
    public ModelAndView edit(
            ModelMap model, @PathVariable("user_id") Integer userId,
            RedirectAttributes redirectAttributes, @Valid @ModelAttribute("user") UserModel user,
            BindingResult result
    ) {

        if (user != null) {
            UserEntity entity = userService.findById(userId).get();
            if (entity != null) {
                // Cập nhật thông tin người dùng
                entity.setName(user.getName());
                entity.setSurname(user.getSurname());
                entity.setGender(user.getGender());
                entity.setPhoneNumber(user.getPhoneNumber());

                userService.save(entity);
                redirectAttributes.addFlashAttribute("message", "Thông tin đã được cập nhật thành công!");
                redirectAttributes.addFlashAttribute("status", "success");
            }
        }
        return new ModelAndView("redirect:/user_infor/" + userId);

    }

}