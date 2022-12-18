package mock;

import rent.calculator.com.model.dto.RentPriceDTO;

import java.math.BigDecimal;

public class MockPrices {

    public static RentPriceDTO mockActualRentPrice() {
        return RentPriceDTO.builder()
                .actual(true)
                .electricity(BigDecimal.valueOf(1.02))
                .gas(BigDecimal.valueOf(7.46))
                .water(BigDecimal.valueOf(28.13))
                .rent(BigDecimal.valueOf(1800.00))
                .build();
    }
}
