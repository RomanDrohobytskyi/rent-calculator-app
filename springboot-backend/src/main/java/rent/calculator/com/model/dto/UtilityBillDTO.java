package rent.calculator.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rent.calculator.com.model.enums.UtilityBillType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilityBillDTO {
    private UtilityBillType utilityType;
    private BigDecimal meterState;
    private BigDecimal consumption;
    private BigDecimal cost;
}
