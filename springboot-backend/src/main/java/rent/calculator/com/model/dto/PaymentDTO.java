package rent.calculator.com.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.util.CollectionUtils;
import rent.calculator.com.model.enums.PaymentState;
import rent.calculator.com.model.enums.UtilityBillType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private BigDecimal total;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    @Builder.Default
    private PaymentState state = PaymentState.NEW;
    private String emailMessage;
    private Set<UtilityBillDTO> utilityBills = new HashSet<>();

    public Optional<UtilityBillDTO> findFirstUtilityBillByType(UtilityBillType type) {
        if (Objects.isNull(type) || CollectionUtils.isEmpty(utilityBills)) {
            return Optional.empty();
        }
        return this.getUtilityBills().stream()
                .filter(previousUtilityBill -> previousUtilityBill.getUtilityType().equals(type))
                .findFirst();
    }
}