package rent.calculator.com.service;

import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;

import static rent.calculator.com.utils.CommonUtils.getMonth;

@Service
public class PaymentMessageCreationService {
    private final PaymentMessageFormatter messageFormatter;
    private final PaymentService paymentService;

    public PaymentMessageCreationService(PaymentService paymentService, RentPriceService rentPriceService, PaymentMessageService paymentMessageService) {
        this.paymentService = paymentService;
        this.messageFormatter = new PaymentMessageFormatter(rentPriceService.getActual(), paymentMessageService.getActual());
    }

    public String createMessage(PaymentDTO payment) {
        String month = getMonth(payment);

        return paymentService.findPrevious(payment)
                .map(previousPayment -> createMessage(payment, month, previousPayment))
                .orElseGet(() -> createMessage(payment, month, payment));
    }

    private String createMessage(PaymentDTO payment, String month, PaymentDTO previousPayment) {
        return messageFormatter.formatTitle(month) + "\n"
                + messageFormatter.formatDescription(month) + "\n"
                + messageFormatter.formatMedia(payment) + "\n"
                + messageFormatter.formatWater(payment, previousPayment) + "\n"
                + messageFormatter.formatGas(payment, previousPayment) + "\n"
                + messageFormatter.formatElectricity(payment, previousPayment) + "\n"
                + messageFormatter.formatTotal(payment) + "\n"
                + messageFormatter.formatRegards();
    }

}
