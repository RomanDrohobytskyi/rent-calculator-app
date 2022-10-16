package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.service.PaymentMessageService;
import rent.calculator.com.service.RentPriceService;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rent/calculator/configuration/")
@RequiredArgsConstructor
public class ConfigurationController {
    private final RentPriceService rentPriceService;
    private final PaymentMessageService paymentMessageService;

    /*
    TODO:
        1: restore backup from file
     */

    @GetMapping
    public void onConfiguration() {
        // display prices and messages
    }

    @GetMapping("/messages")
    public PaymentMessageDTO getMessages() {
        return paymentMessageService.getActual();
    }

    @GetMapping("/prices")
    public RentPriceDTO getPrices() {
        return rentPriceService.getActual();
    }

    /*
    *     @PutMapping("/payments/update/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updated = paymentService.update(paymentDTO);
        return ok(updated);
    }*/

    @PutMapping("/update/messages/{id}")
    public ResponseEntity<PaymentMessageDTO> updateMessages(@PathVariable Long id, @RequestBody PaymentMessageDTO paymentMessageDTO) {
        PaymentMessageDTO updated = paymentMessageService.update(paymentMessageDTO);
        return ok(updated);
    }

    @PutMapping("/update/prices/{id}")
    public void updatePrices() {
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
