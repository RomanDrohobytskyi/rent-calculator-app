package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.payment.facade.PaymentCreationFacade;
import rent.calculator.com.service.PaymentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rent/calculator/")
@RequiredArgsConstructor
public class RentCalculatorController {
    private final PaymentService paymentService;
    private final PaymentCreationFacade paymentCreationFacade;

/*TODO
*  main controller
*  add monthly rent data
*  edit
*  archive
*  */

    @GetMapping("/payments")
    public List<PaymentDTO> payments() {
        return paymentService.findAll();
    }

    @GetMapping("/payments/details/{id}")
    public ResponseEntity<PaymentDTO> paymentDetails(@PathVariable Long id) {
        return of(paymentService.findById(id));
    }

    @PutMapping("/payments/update/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updated = paymentCreationFacade.update(paymentDTO);
        return ok(updated);
    }

    @PutMapping("/payments/recalculate")
    public PaymentDTO recalculate(@RequestBody PaymentDTO paymentDTO) {
        return paymentCreationFacade.recalculate(paymentDTO);
    }

    @DeleteMapping("/payments/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        paymentCreationFacade.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
