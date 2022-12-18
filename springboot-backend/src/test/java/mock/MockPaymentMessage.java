package mock;

import rent.calculator.com.model.dto.PaymentMessageDTO;

import static rent.calculator.com.model.dto.PaymentMessageDTO.builder;

public class MockPaymentMessage {

    public static PaymentMessageDTO mockPaymentMessage() {
        return builder()
                .id(-1L)
                .title("Payment Confirmation, Month %s")
                .description("Please find payment confirmation in attachments, for month %s.")
                .totalMedia("Total media %s,")
                .water("Water %s,")
                .gas("Gas %s,")
                .electricity("Electricity %s,")
                .total("Total - %s,")
                .regards("Regards")
                .actual(true)
                .build();
    }
}
