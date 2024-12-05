package hcmute.controller.admin;

import hcmute.entity.PayMethodEntity;
import hcmute.model.PayMethodModel;
import hcmute.service.IPayMethodService;
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
@RequestMapping("admin/paymethod")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PaymethodAdminController {

    IPayMethodService payMethodService;

    @GetMapping("")
    public String IndexViewMethod(ModelMap model) {
        List<PayMethodEntity> paymethods = payMethodService.findAll();
        model.addAttribute("paymethods", paymethods);
        return "admin/view/view-paymethod";
    }

    @GetMapping("add")
    public String add(ModelMap model) {
        PayMethodModel paymethod = new PayMethodModel();
        paymethod.setIsEdit(false);
        model.addAttribute("paymethod", paymethod);
        return "admin/customize/customize-paymethod";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(
            ModelMap model, @Valid @ModelAttribute("payMethod") PayMethodModel payMethod,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/customize/customize-paymethod");
        }
        if (payMethod != null) {
            PayMethodEntity entity = new PayMethodEntity();
            if (payMethod.getIdPayMethod() != null) {
                entity.setIdPayMethod(payMethod.getIdPayMethod());
            }
            if (payMethod.getName() != null) {
                entity.setName(payMethod.getName());
            }

            payMethodService.save(entity);
            String message = payMethod.getIsEdit() ? "paymethod đã được cập nhật thành công"
                    : "paymethod đã được thêm thành công";
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", "Không thể lưu paymethod với dữ liệu null");
        }

        return new ModelAndView("redirect:/admin/paymethod", model);
    }

    @GetMapping("edit/{idPayMethod}")
    public ModelAndView edit(ModelMap model, @PathVariable("idPayMethod") String idPayMethod) {
        Optional<PayMethodEntity> opt = payMethodService.findById(idPayMethod);
        PayMethodModel paymethod = new PayMethodModel();
        if (opt.isPresent()) {
            PayMethodEntity entity = opt.get();
            BeanUtils.copyProperties(entity, paymethod);
            paymethod.setIsEdit(true);
            model.addAttribute("paymethod", paymethod);
            return new ModelAndView("admin/customize/customize-paymethod", model);
        }

        model.addAttribute("message", "Branch không tồn tại");
        return new ModelAndView("forward:/admin/paymethod", model);
    }

}
