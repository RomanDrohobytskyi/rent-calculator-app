package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;

import static rent.calculator.com.utils.DateUtils.getStringRepresentationOfPaymentMonth;

@Service
@RequiredArgsConstructor
public class PaymentMessageCreationService {
    private final PaymentService paymentService;
    private final PaymentMessageService paymentMessageService;
    private final RentPriceService rentPriceService;

    public String createMessage(PaymentDTO payment) {
        String month = getStringRepresentationOfPaymentMonth(payment);

        return paymentService.findPrevious(payment)
                .map(previousPayment -> createMessage(payment, month, previousPayment))
                .orElseGet(() -> createMessage(payment, month, payment));
    }

    private String createMessage(PaymentDTO payment, String month, PaymentDTO previousPayment) {
        PaymentMessageFormatter messageFormatter = new PaymentMessageFormatter(
                rentPriceService.getActual(), paymentMessageService.getActual());

        return messageFormatter
                .formatTitle(month)
                .formatDescription(month)
                .formatMedia(payment)
                .formatWater(payment, previousPayment)
                .formatGas(payment, previousPayment)
                .formatElectricity(payment, previousPayment)
                .formatTotal(payment)
                .formatRegards()
                .build();
    }

}
