package rent.calculator.com.payment.message;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.service.RentPriceService;

import static rent.calculator.com.utils.DateUtils.getStringRepresentationOfPaymentMonth;

@Service
@RequiredArgsConstructor
public class PaymentMessageCreationService {
//    private final PaymentService paymentService;
    private final PaymentMessageService paymentMessageService;
    private final RentPriceService rentPriceService;

    public String createMessage(PaymentDTO currentPayment, PaymentDTO previousPayment) {
        String month = getStringRepresentationOfPaymentMonth(currentPayment);
        return createMessage(currentPayment, month, ObjectUtils.defaultIfNull(previousPayment, currentPayment));
    }

    private String createMessage(PaymentDTO payment, String month, PaymentDTO previousPayment) {
        PaymentMessageFormatter messageFormatter = new PaymentMessageFormatter(
                rentPriceService.getActual(), paymentMessageService.getActual());

        return messageFormatter
                .formatTitle(month)
                .formatDescription(month)
                .formatMedia(payment)
                .formatUtilityBills(payment, previousPayment)
                .formatTotal(payment)
                .formatRegards()
                .build();
    }

}
