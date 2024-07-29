package com.demo.resilencia.service;

import com.demo.resilencia.exception.NotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImp implements PaymentService {
    private final static String URI = "http://localhost:8090/api-demo/processPayment/";
    private RestTemplate restTemplate;

    @Autowired
    public PaymentServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "processPayment", fallbackMethod = "fallbackMethod")
    public String processPayment(String opcionEvent) throws Exception {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URI.concat(opcionEvent), String.class);

            return response.getBody();
        } catch (Exception ex) {
            log.error("EX " + ex.getMessage());
            throw ex;
        }
    }


    public String fallbackMethod(Exception throwable) {
        System.out.println("fallbackMethod");
        log.error("FB " + throwable.getMessage());
        log.error("FB " + throwable.getCause());
        return "Lo sentimos, actualmente estamos experimentando dificultades técnicas para procesar pagos en línea. Por favor, inténtalo de nuevo más tarde. Agradecemos tu paciencia y comprensión.";
    }
}
