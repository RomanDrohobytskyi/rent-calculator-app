package rent.calculator.com.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentPriceDTO {
    private Long id;
    private BigDecimal gas;
    private BigDecimal water;
    private BigDecimal electricity;
    private BigDecimal rent;
    private boolean actual;
}
