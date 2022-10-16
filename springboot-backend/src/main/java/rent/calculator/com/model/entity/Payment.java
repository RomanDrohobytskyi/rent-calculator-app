package rent.calculator.com.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;
import rent.calculator.com.model.enums.PaymentState;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
    private BigDecimal gas;
    private BigDecimal gasQuantity;
    private BigDecimal gasBill;
    private BigDecimal water;
    private BigDecimal waterQuantity;
    private BigDecimal waterBill;
    private BigDecimal electricity;
    private BigDecimal electricityQuantity;
    private BigDecimal electricityBill;
    private BigDecimal total;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDate paymentDate;
    @Builder.Default
    private PaymentState state = PaymentState.NEW;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String emailMessage;
}
