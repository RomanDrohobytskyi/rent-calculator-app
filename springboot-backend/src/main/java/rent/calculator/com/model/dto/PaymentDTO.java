package rent.calculator.com.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import rent.calculator.com.model.enums.PaymentState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    @Builder.Default
    private PaymentState state = PaymentState.NEW;
    private String emailMessage;
}