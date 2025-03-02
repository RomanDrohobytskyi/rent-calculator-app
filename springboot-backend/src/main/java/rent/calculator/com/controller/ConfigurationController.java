package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.payment.message.PaymentMessageService;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rent/calculator/configuration/")
@RequiredArgsConstructor
public class ConfigurationController {
    private final PaymentMessageService paymentMessageService;

    /*
    TODO:
        1: restore backup from file
     */

    @GetMapping("/messages")
    public PaymentMessageDTO getMessages() {
        return paymentMessageService.getActual();
    }

    @PutMapping("/update/messages/{id}")
    public ResponseEntity<PaymentMessageDTO> updateMessages(@PathVariable Long id, @RequestBody PaymentMessageDTO paymentMessageDTO) {
        PaymentMessageDTO updated = paymentMessageService.update(paymentMessageDTO);
        return ok(updated);
    }

    @PutMapping("/restore/backup")
    public void restoreBackup() {
    }

    @PostMapping("/backup")
    public ResponseEntity<Object> backup() {
        //Generate Excel file (and/or create db dump)
        //TODO refactor
        return ok("Successful backup");
    }
}
