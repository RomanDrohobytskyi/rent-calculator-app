package rent.calculator.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import rent.calculator.com.TestDatabaseInitializer;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.UtilityBillDTO;
import rent.calculator.com.model.enums.UtilityBillType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class PaymentCreationServiceTest extends TestDatabaseInitializer {

    @Autowired
    private PaymentCreationService paymentCreationService;

    @Test
    public void shouldCreatePayment() {
        // given
        UtilityBillDTO gasBill = UtilityBillDTO.builder()
                .utilityType(UtilityBillType.GAS)
                .meterState(BigDecimal.valueOf(10))
                .build();

        PaymentDTO payment = PaymentDTO.builder()
                .paymentDate(LocalDate.now())
                .creationDate(LocalDateTime.now())
                .utilityBills(Set.of(gasBill))
                .build();
        // when
        PaymentDTO savedPayment = paymentCreationService.save(payment);

        // then
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getPaymentDate()).isEqualTo(payment.getPaymentDate());
        assertThat(savedPayment.getUtilityBills()).isNotEmpty();
        assertThat(savedPayment.getUtilityBills()).hasSize(1);
    }


}
