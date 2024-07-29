package com.demo.resilencia.controller;

import com.demo.resilencia.service.PaymentService;
import com.demo.resilencia.service.PaymentServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired

    public PaymentController(PaymentServiceImp paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/processPayment/{opcionEvent}")
    public String processPayment(@PathVariable  String opcionEvent) throws Exception {
            return paymentService.processPayment(opcionEvent);
    }

    public String fallbackMethod(Throwable throwable) {
        return "Lo sentimos, actualmente estamos experimentando dificultades técnicas para procesar pagos en línea. Por favor, inténtalo de nuevo más tarde. Agradecemos tu paciencia y comprensión.";
    }

}