package rent.calculator.com.model.entity;

import lombok.*;
import rent.calculator.com.model.enums.UtilityBillType;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilityBill extends BaseEntity {
    @NotNull
    private UtilityBillType utilityType;
    private BigDecimal meterState;
    private BigDecimal consumption;
    private BigDecimal cost;
}
