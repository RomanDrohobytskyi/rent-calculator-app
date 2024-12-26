package rent.calculator.com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Electricity {
    private BigDecimal electricity;
    private BigDecimal electricityQuantity;
    private BigDecimal electricityBill;
}
