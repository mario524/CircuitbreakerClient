package co.com.coding.togegher.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImp implements PaymentService {
    private final static String URI = "http://localhost:8090/api-demo/processPayment";
    private RestTemplate restTemplate;

    @Autowired
    public PaymentServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "processPayment", fallbackMethod = "fallbackMethod")
    public String processPayment() throws Exception {
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(URI, String.class);
            //Thread.sleep(20000);
            return response.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }


    public String fallbackMethod(Throwable throwable) {
        return "Lo sentimos, actualmente estamos experimentando dificultades técnicas para procesar pagos en línea. Por favor, inténtalo de nuevo más tarde. Agradecemos tu paciencia y comprensión.";
    }
}
