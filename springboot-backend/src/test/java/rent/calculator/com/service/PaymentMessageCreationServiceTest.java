package rent.calculator.com.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import rent.calculator.com.model.dto.PaymentDTO;

import java.util.Optional;

import static mock.MockPaymentMessage.mockPaymentMessage;
import static mock.MockPayments.mockPreviousPayment;
import static mock.MockPrices.mockActualRentPrice;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentMessageCreationServiceTest {
    @InjectMocks
    PaymentMessageCreationService paymentMessageCreationService;
    @Mock
    PaymentMessageService paymentMessageService;
    @Mock
    RentPriceService rentPriceService;
    @Mock
    PaymentService paymentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateMessage() {
        // given
        PaymentDTO previousPayment = mockPreviousPayment();

        when(paymentService.findPrevious(any())).thenReturn(Optional.of(mockPreviousPayment()));
        when(rentPriceService.getActual()).thenReturn(mockActualRentPrice());
        when(paymentMessageService.getActual()).thenReturn(mockPaymentMessage());

        //when
        String paymentMessage = paymentMessageCreationService.createMessage(previousPayment);

        //then
        assertThat(paymentMessage).isNotBlank();
        assertThat(paymentMessage).isEqualTo("Payment Confirmation, Month grudnia\n" +
                "Please find payment confirmation in attachments, for month grudnia.\n" +
                "Total media 219.32,\n" +
                "Water 1077.11,\n" +
                "Gas 768.64,\n" +
                "Electricity 373.5,\n" +
                "Total - 2019.32,\n" +
                "Regards");
    }

}
