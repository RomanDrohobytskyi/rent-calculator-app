package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.dto.RentPriceDTO;

import java.math.BigDecimal;

import static java.lang.String.format;
import static rent.calculator.com.utils.FormattingUtils.format;


/*TODO refactor to decorator*/
@RequiredArgsConstructor
public class PaymentMessageFormatter {

    private final static String NEW_LINE = "\n";

    private final RentPriceDTO price;
    private final PaymentMessageDTO message;

    private StringBuilder messageBuilder = new StringBuilder();

    public String build() {
        return messageBuilder.toString();
    }

    public PaymentMessageFormatter formatMedia(PaymentDTO payment) {
        messageBuilder.append(format(message.getTotalMedia(), sumMediaAndFormat(payment)))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatTotal(PaymentDTO payment) {
        messageBuilder.append(format(message.getTotal(), getTotal(payment, price)))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatDescription(String month) {
        messageBuilder.append(format(message.getDescription(), month))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatTitle(String month) {
        messageBuilder.append(format(message.getTitle(), month))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatWater(PaymentDTO payment, PaymentDTO previousPayment) {
        messageBuilder.append(format(message.getWater(), format(payment.getWater()),
                        format(previousPayment.getWater()),
                        format(payment.getWater().subtract(previousPayment.getWater()))))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatGas(PaymentDTO payment, PaymentDTO previousPayment) {
        messageBuilder.append(format(message.getGas(), format(payment.getGas()),
                        format(previousPayment.getGas()),
                        format(payment.getGas().subtract(previousPayment.getGas()))))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatElectricity(PaymentDTO payment, PaymentDTO previousPayment) {
        messageBuilder.append(format(message.getElectricity(), format(payment.getElectricity()),
                        format(previousPayment.getElectricity()),
                        format(payment.getElectricity().subtract(previousPayment.getElectricity()))))
                .append(NEW_LINE);
        return this;
    }

    public PaymentMessageFormatter formatRegards() {
        messageBuilder.append(message.getRegards());
        return this;
    }

    private String sumMediaAndFormat(PaymentDTO payment) {
        BigDecimal sum = sumMedia(payment);
        return format(sum);
    }

    private String getTotal(PaymentDTO paymentDTO, RentPriceDTO price) {
        BigDecimal total = sumMedia(paymentDTO).add(price.getRent());
        return format(total);
    }

    private BigDecimal sumMedia(PaymentDTO payment) {
        return payment.getGasBill()
                .add(payment.getElectricityBill())
                .add(payment.getWaterBill());
    }

}
