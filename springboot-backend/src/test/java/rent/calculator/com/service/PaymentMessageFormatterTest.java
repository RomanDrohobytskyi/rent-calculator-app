package rent.calculator.com.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import rent.calculator.com.model.dto.PaymentDTO;

import static mock.MockPaymentMessage.mockPaymentMessage;
import static mock.MockPayments.mockActualPayment;
import static mock.MockPayments.mockPreviousPayment;
import static mock.MockPrices.mockActualRentPrice;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class PaymentMessageFormatterTest {
    PaymentMessageFormatter messageFormatter;
    PaymentDTO mockPayment;
    PaymentDTO mockPreviousPayment;

    @Before
    public void init() {
        this.mockPayment = mockActualPayment();
        this.mockPreviousPayment = mockPreviousPayment();
        messageFormatter = new PaymentMessageFormatter(mockActualRentPrice(), mockPaymentMessage());
    }

    @Test
    public void shouldFormatMedia() {
        //when
        String resultMessages = messageFormatter.formatMedia(mockPayment);

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Total media 239.46,");
    }

    @Test
    public void shouldFormatTotal() {
        //when
        String resultMessages = messageFormatter.formatTotal(mockPayment);

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Total - 2039.46,");
    }

    @Test
    public void shouldFormatDescription() {
        //when
        String resultMessages = messageFormatter.formatDescription("December");

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Please find payment confirmation in attachments, for month December.");
    }

    @Test
    public void shouldFormatTitle() {
        //when
        String resultMessages = messageFormatter.formatTitle("December");

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Payment Confirmation, Month December");
    }

    @Test
    public void shouldFormatWater() {
        //when
        String resultMessages = messageFormatter.formatWater(mockPayment, mockPreviousPayment);

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Water 1081.08,");
    }

    @Test
    public void shouldFormatGas() {
        //when
        String resultMessages = messageFormatter.formatGas(mockPayment, mockPreviousPayment);

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Gas 775.7,");
    }

    @Test
    public void shouldFormatElectricity() {
        //when
        String resultMessages = messageFormatter.formatElectricity(mockPayment, mockPreviousPayment);

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Electricity 427.5,");
    }

    @Test
    public void shouldFormatRegards() {
        //when
        String resultMessages = messageFormatter.formatRegards();

        //then
        assertThat(resultMessages).isNotBlank();
        assertThat(resultMessages).isEqualTo("Regards");
    }
}
