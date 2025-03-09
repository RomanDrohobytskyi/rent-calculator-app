package rent.calculator.com.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;
import rent.calculator.com.model.enums.PaymentState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
    private BigDecimal total;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDate paymentDate;
    @Builder.Default
    private PaymentState state = PaymentState.NEW;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String emailMessage;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @Builder.Default
    private Set<UtilityBill> utilityBills = new HashSet<>();
}
