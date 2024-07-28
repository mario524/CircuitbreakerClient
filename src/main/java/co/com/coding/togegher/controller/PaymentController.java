package co.com.coding.togegher.controller;

import co.com.coding.togegher.service.PaymentService;
import co.com.coding.togegher.service.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired

    public PaymentController(PaymentServiceImp paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/processPayment")
    public String processPayment() throws Exception {
            return paymentService.processPayment();
    }

    public String fallbackMethod(Throwable throwable) {
        return "Lo sentimos, actualmente estamos experimentando dificultades técnicas para procesar pagos en línea. Por favor, inténtalo de nuevo más tarde. Agradecemos tu paciencia y comprensión.";
    }

}