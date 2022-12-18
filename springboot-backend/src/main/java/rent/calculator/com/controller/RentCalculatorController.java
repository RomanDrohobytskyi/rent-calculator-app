package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.service.PaymentCreationService;
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
    private final PaymentCreationService paymentCreationService;

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

    @PostMapping("/payments/add")
    public PaymentDTO add(@RequestBody PaymentDTO paymentDTO) {
        return paymentCreationService.save(paymentDTO);
    }

    @GetMapping("/payments/details/{id}")
    public ResponseEntity<PaymentDTO> paymentDetails(@PathVariable Long id) {
        return of(paymentService.findByIdAndMapToDTO(id));
    }

    @PutMapping("/payments/update/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updated = paymentService.update(paymentDTO);
        return ok(updated);
    }

    @PutMapping("/payments/recalculate")
    public PaymentDTO recalculate(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.recalculate(paymentDTO);
    }

    @DeleteMapping("/payments/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        paymentService.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
