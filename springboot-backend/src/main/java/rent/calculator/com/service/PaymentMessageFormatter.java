package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.dto.RentPriceDTO;

import java.math.BigDecimal;

import static rent.calculator.com.utils.FormattingUtils.format;


/*TODO refactor to decorator*/
@Service
@RequiredArgsConstructor
public class PaymentMessageFormatter {
    private final RentPriceDTO price;
    private final PaymentMessageDTO message;

    public String formatMedia(PaymentDTO payment) {
        return String.format(message.getTotalMedia(), sumMediaAndFormat(payment));
    }

    public String formatTotal(PaymentDTO payment) {
        return String.format(message.getTotal(), getTotal(payment, price));
    }

    public String formatDescription(String month) {
        return String.format(message.getDescription(), month);
    }

    public String formatTitle(String month) {
        return String.format(message.getTitle(), month);
    }

    public String formatWater(PaymentDTO payment, PaymentDTO previousPayment) {
        return String.format(message.getWater(), format(payment.getWater()),
                format(previousPayment.getWater()),
                format(payment.getWater().subtract(previousPayment.getWater())));
    }

    public String formatGas(PaymentDTO payment, PaymentDTO previousPayment) {
        return String.format(message.getGas(), format(payment.getGas()),
                format(previousPayment.getGas()),
                format(payment.getGas().subtract(previousPayment.getGas())));
    }

    public String formatElectricity(PaymentDTO payment, PaymentDTO previousPayment) {
        return String.format(message.getElectricity(), format(payment.getElectricity()),
                format(previousPayment.getElectricity()),
                format(payment.getElectricity().subtract(previousPayment.getElectricity())));
    }

    public String formatRegards() {
        return this.message.getRegards();
    }

    public static String sumMediaAndFormat(PaymentDTO payment) {
        BigDecimal sum = sumMedia(payment);
        return format(sum);
    }

    public static String getTotal(PaymentDTO paymentDTO, RentPriceDTO price) {
        BigDecimal total = sumMedia(paymentDTO).add(price.getRent());
        return format(total);
    }

    public static BigDecimal sumMedia(PaymentDTO payment) {
        return payment.getGasBill()
                .add(payment.getElectricityBill())
                .add(payment.getWaterBill());
    }
}
