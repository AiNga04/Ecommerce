package hcmute.presentation.mvc.manager;

import hcmute.entity.BranchEntity;
import hcmute.entity.UserEntity;
import hcmute.service.IUserService;
import hcmute.service.impl.CookieServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("manager")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ManagerController {
    CookieServiceImpl cookieServiceImpl;
    IUserService userService;

    @GetMapping("index")
    public String indexManager(ModelMap model) {
        int userId = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
        Optional<UserEntity> opt = userService.findById(userId);
        if (opt.isPresent()) {
            UserEntity entity = opt.get();
            BranchEntity branch = entity.getBranchByUser();
            model.addAttribute("branch", branch);
        }
        return "manager/index";
    }

}
