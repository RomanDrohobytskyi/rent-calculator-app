package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.dto.RentPriceDTO;

import static rent.calculator.com.service.PaymentCalculationService.getTotal;
import static rent.calculator.com.service.PaymentCalculationService.sumMediaAndFormat;
import static rent.calculator.com.utils.CommonUtils.getMonth;
import static rent.calculator.com.utils.FormattingUtils.format;

@Service
@RequiredArgsConstructor
public class PaymentMessageCreationService {
    private final PaymentMessageService paymentMessageService;
    private final RentPriceService rentPriceService;
    private final PaymentService paymentService;

    private PaymentMessageDTO message;
    private RentPriceDTO price;

    public String createMessage(PaymentDTO payment) {
        this.message = paymentMessageService.getActual();
        this.price = rentPriceService.getActual();

        String month = getMonth(payment);

        PaymentDTO previousPayment = paymentService.findPrevious(payment)
                .orElseThrow(IllegalArgumentException::new);

        return createMessage(payment, month, previousPayment);
    }

    private String createMessage(PaymentDTO payment, String month, PaymentDTO previousPayment) {
        return formatTitle(month) + "\n"
                + formatDescription(month) + "\n"
                + formatMedia(payment) + "\n"
                + formatWater(payment, previousPayment, message) + "\n"
                + formatGas(payment, previousPayment, message) + "\n"
                + formatElectricity(payment, previousPayment, message) + "\n"
                + formatTotal(payment) + "\n"
                + message.getRegards();
    }

    private String formatMedia(PaymentDTO payment) {
        return String.format(message.getTotalMedia(), sumMediaAndFormat(payment, price));
    }

    private String formatTotal(PaymentDTO payment) {
        return String.format(message.getTotal(), getTotal(payment, price));
    }

    private String formatDescription(String month) {
        return String.format(message.getDescription(), month);
    }

    private String formatTitle(String month) {
        return String.format(message.getTitle(), month);
    }

    private String formatWater(PaymentDTO payment, PaymentDTO previousPayment, PaymentMessageDTO message) {
        return String.format(message.getWater(), format(payment.getWater()),
                format(previousPayment.getWater()),
                format(payment.getWater().subtract(previousPayment.getWater())));
    }

    private String formatGas(PaymentDTO payment, PaymentDTO previousPayment, PaymentMessageDTO message) {
        return String.format(message.getGas(), format(payment.getGas()),
                format(previousPayment.getGas()),
                format(payment.getGas().subtract(previousPayment.getGas())));
    }

    private String formatElectricity(PaymentDTO payment, PaymentDTO previousPayment, PaymentMessageDTO message) {
        return String.format(message.getElectricity(), format(payment.getElectricity()),
                    format(previousPayment.getElectricity()),
                    format(payment.getElectricity().subtract(previousPayment.getElectricity())));
    }

}
