package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.PaymentMessageDTO;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.model.dto.UtilityBillDTO;
import rent.calculator.com.model.enums.UtilityBillType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

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

    public PaymentMessageFormatter formatUtilityBills(PaymentDTO payment, PaymentDTO previousPayment) {
        Map<UtilityBillType, String> messageByType = message.getUtilityBillsMessages();
        for (UtilityBillDTO utilityBill : payment.getUtilityBills()) {
            String message = messageByType.get(utilityBill.getUtilityType());
            BigDecimal previousBillMeterState = previousPayment.findFirstUtilityBillByType(utilityBill.getUtilityType())
                    .map(UtilityBillDTO::getMeterState)
                    .orElse(BigDecimal.ZERO);

            messageBuilder.append(format(message, format(utilityBill.getMeterState()),
                            format(previousBillMeterState),
                            format(Objects.requireNonNullElse(utilityBill.getConsumption(), BigDecimal.ZERO))))
                    .append(NEW_LINE);        }
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
        return payment.getUtilityBills().stream()
                .map(UtilityBillDTO::getCost)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
