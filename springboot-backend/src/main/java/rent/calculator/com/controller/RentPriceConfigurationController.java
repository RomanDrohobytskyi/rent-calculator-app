package rent.calculator.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.service.RentPriceService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rent/calculator/configuration/price")
@RequiredArgsConstructor
public class RentPriceConfigurationController {
    private final RentPriceService rentPriceService;

    @GetMapping
    public RentPriceDTO getActualPrice() {
        return rentPriceService.getActual();
    }

    @GetMapping("/archival")
    public List<RentPriceDTO> getArchivalPrices() {
        return rentPriceService.getArchivalPrices();
    }

    @PutMapping("/update")
    public ResponseEntity<RentPriceDTO> addRentPrice(@RequestBody RentPriceDTO rentPrice) {
        RentPriceDTO updatedRentPrice = rentPriceService.addActualRentPrice(rentPrice);
        return ok(rentPrice);
    }
}
