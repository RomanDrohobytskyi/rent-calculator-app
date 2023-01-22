package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.service.PaymentCreationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rent/calculator/payments")
@RequiredArgsConstructor
public class PaymentCreationController {
    private final PaymentCreationService paymentCreationService;

    @PostMapping("/add")
    public PaymentDTO add(@RequestBody PaymentDTO paymentDTO) {
        return paymentCreationService.save(paymentDTO);
    }
}
