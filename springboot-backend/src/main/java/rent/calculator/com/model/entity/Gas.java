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
public class Gas {
    private BigDecimal gas;
    private BigDecimal gasQuantity;
    private BigDecimal gasBill;
}
