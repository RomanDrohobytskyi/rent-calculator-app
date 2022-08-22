package rent.calculator.com.service;

import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.RentPriceDTO;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static rent.calculator.com.utils.FormattingUtils.format;

public class PaymentCalculationService {

    public static String sumMediaAndFormat(PaymentDTO payment, RentPriceDTO price) {
        BigDecimal sum = sumMedia(payment, price);
        return format(sum);
    }

    public static String getTotal(PaymentDTO paymentDTO, RentPriceDTO price) {
        BigDecimal total = sumMedia(paymentDTO, price).add(valueOf(1400));
        return format(total);
    }

    public static BigDecimal sumMedia(PaymentDTO payment, RentPriceDTO price) {
        BigDecimal water = payment.getWater().multiply(price.getWater());
        BigDecimal electricity = payment.getElectricity().multiply(price.getElectricity());
        BigDecimal gas = payment.getGas().multiply(price.getGas());
        return water.add(electricity).add(gas);
    }
}
