package mock;

import rent.calculator.com.model.dto.PaymentDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static rent.calculator.com.model.dto.PaymentDTO.builder;

public class MockPayments {

    public static PaymentDTO mockActualPayment() {
        return builder()
                .water(BigDecimal.valueOf(1081.08))
                .electricity(BigDecimal.valueOf(427.5))
                .gas(BigDecimal.valueOf(775.7))
                .paymentDate(LocalDate.now())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static PaymentDTO mockPreviousPayment() {
        return builder()
                .water(BigDecimal.valueOf(1077.11))
                .waterBill(BigDecimal.valueOf(111.54))
                .electricity(BigDecimal.valueOf(373.5))
                .electricityBill(BigDecimal.valueOf(55.08))
                .gas(BigDecimal.valueOf(768.64))
                .gasBill(BigDecimal.valueOf(52.70))
                .paymentDate(LocalDate.now())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static PaymentDTO mockPreviousPreviousPayment() {
        return builder()
                .water(BigDecimal.valueOf(1070.58))
                .electricity(BigDecimal.valueOf(284.4))
                .gas(BigDecimal.valueOf(756.97))
                .paymentDate(LocalDate.now())
                .creationDate(LocalDateTime.now())
                .build();
    }

}
