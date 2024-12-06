package hcmute.presentation.mvc.user;

import hcmute.entity.BranchEntity;
import hcmute.service.IBranchService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("branch-info")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class BranchesInfoController {

    IBranchService branchService;

    @GetMapping("/{idBranch}")
    public String listByCity(ModelMap model, @PathVariable("idBranch") Integer idBranch) {
        BranchEntity branch = branchService.getById(idBranch);
        model.addAttribute("branch", branch);
        return "user/branches_info";
    }
}
