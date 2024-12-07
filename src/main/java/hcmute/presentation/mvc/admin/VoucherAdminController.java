package hcmute.presentation.mvc.admin;

import hcmute.dto.request.VoucherAdminRequest;
import hcmute.dto.response.ListVoucherAdminResponse;
import hcmute.dto.response.VoucherAdminResponse;
import hcmute.model.enums.Status;
import hcmute.model.enums.VoucherType;
import hcmute.service.IVoucherAdminService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/voucher")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class VoucherAdminController {

    IVoucherAdminService voucherAdminService;

    @GetMapping("/add")
    public String showAddVoucherForm(Model model) {
        model.addAttribute("voucher", new VoucherAdminRequest());
        return "admin/customize/customize-voucher-add";
    }

    @PostMapping("/add")
    public String addNewVoucherAdmin(
            @ModelAttribute("voucher") VoucherAdminRequest request,
            HttpServletRequest servletRequest, Model model
    ) {
//        String username = (String) servletRequest.getAttribute("username");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        request.setUsername(username);
        request.validateCreate();

        VoucherAdminResponse response = voucherAdminService.addNewVoucherAdmin(request);
        model.addAttribute("response", response);
        return "redirect:/admin/voucher";
    }

    @GetMapping("/edit/{voucherId}")
    public String showEditVoucherForm(@PathVariable Long voucherId, Model model) {
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã của mã giảm giá không được để trống!");
        }
        VoucherAdminResponse response = voucherAdminService.getVoucherAdminByVoucherId(voucherId);
        model.addAttribute("voucher", response);
        return "admin/customize/customize-voucher-edit";
    }

    @PostMapping("/edit")
    public String updateVoucherAdmin(
            @ModelAttribute("voucher") VoucherAdminRequest request,
            HttpServletRequest servletRequest, Model model
    ) {
//        String username = (String) servletRequest.getAttribute("username");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        request.setUsername(username);
        request.validateUpdate();

        VoucherAdminResponse response = voucherAdminService.updateVoucherAdmin(request, username);
        model.addAttribute("response", response);
        return "redirect:/admin/voucher";
    }

    @GetMapping("")
    public String getAllVoucherAdmin(HttpServletRequest servletRequest, Model model) {
//        String username = (String) servletRequest.getAttribute("username");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ListVoucherAdminResponse response = voucherAdminService.getListVoucherAdmin(username);
        model.addAttribute("vouchers", response.getVoucherDTOs());
        return "admin/view/view-voucher";
    }

    @GetMapping("/by-status")
    public String getAllVoucherAdminByStatus(
            @RequestParam Status status, HttpServletRequest servletRequest, Model model
    ) {
        String username = (String) servletRequest.getAttribute("username");
        ListVoucherAdminResponse response = voucherAdminService.getListVoucherAdminByStatus(username, status);
        model.addAttribute("vouchers", response);
        return "admin/view/view-voucher";
    }

    @GetMapping("/by-type")
    public String getAllVoucherAdminByType(
            @RequestParam VoucherType type, HttpServletRequest servletRequest, Model model
    ) {
        String username = (String) servletRequest.getAttribute("username");
        ListVoucherAdminResponse response = voucherAdminService.getListVoucherAdminByType(username, type);
        model.addAttribute("vouchers", response);
        return "admin/view/view-voucher";
    }

    @PostMapping("/update-status/{voucherId}")
    public String updateStatusVoucherAdmin(
            @PathVariable Long voucherId, @RequestParam Status status,
            HttpServletRequest servletRequest, Model model
    ) {
        String username = (String) servletRequest.getAttribute("username");
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống!");
        }

        VoucherAdminResponse response = voucherAdminService.updateStatusVoucherAdmin(voucherId, status, username);
        model.addAttribute("response", response);
        return "redirect:/admin/voucher";
    }

    @GetMapping("/detail/{voucherId}")
    public String getVoucherAdminByVoucherId(@PathVariable Long voucherId, Model model) {
        if (voucherId == null) {
            throw new IllegalArgumentException("Mã của mã giảm giá không được để trống!");
        }
        VoucherAdminResponse response = voucherAdminService.getVoucherAdminByVoucherId(voucherId);
        model.addAttribute("voucher", response);
        return "admin/voucher/detail-voucher";
    }
}