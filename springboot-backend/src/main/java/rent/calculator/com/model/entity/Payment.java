package rent.calculator.com.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;
import rent.calculator.com.model.payment.state.PaymentState;

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
    private BigDecimal water;
    private BigDecimal total;
    private BigDecimal electricity;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDate paymentDate;
    @Builder.Default
    private PaymentState state = PaymentState.NEW;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String emailMessage;
}
