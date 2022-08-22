package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        1: replace static Java messages with database messages
        2: onConfiguration show all messages and prices with possibility to edit
        3: restore backup from file
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

    @PutMapping("/update/messages")
    public void updateMessages() {
    }

    @PutMapping("/update/prices")
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
