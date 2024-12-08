package hcmute.controller.user;

import hcmute.model.PaymentDTO;
import hcmute.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${spring.application.api-prefix}/payment")
@RequiredArgsConstructor
public class VNPayController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ResponseEntity<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
        PaymentDTO.VNPayResponse response = paymentService.createVnPayPayment(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/vn-pay-callback")
    public ModelAndView payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        ModelAndView modelAndView = new ModelAndView("user/payment-status");

        if ("00".equals(status)) {
            modelAndView.addObject("status", "success");
        } else {
            modelAndView.addObject("status", "failed");
        }

        return modelAndView;
    }
}