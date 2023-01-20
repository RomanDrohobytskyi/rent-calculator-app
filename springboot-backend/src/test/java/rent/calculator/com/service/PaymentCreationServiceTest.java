package rent.calculator.com.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentCreationServiceTest {
    @InjectMocks
    PaymentCreationService paymentCreationService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    PaymentMessageCreationService paymentMessageCreationService;
    @Mock
    PaymentCalculationService paymentCalculationService;
    @Mock
    ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldCreatePayment() {
        //PaymentDTO paymentDTO = mockActualPayment();
        //paymentCreationService.save(paymentDTO);
    }

    PaymentDTO mockPayment() {
        return PaymentDTO.builder()
                .water(BigDecimal.valueOf(1077.11))
                .electricity(BigDecimal.valueOf(373.5))
                .gas(BigDecimal.valueOf(768.64))
                .paymentDate(LocalDate.now())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
