package rent.calculator.com.model.entity;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentPrice extends BaseEntity {
    private BigDecimal gas;
    private BigDecimal water;
    private BigDecimal electricity;
    private BigDecimal rent;
    private boolean actual;
}
