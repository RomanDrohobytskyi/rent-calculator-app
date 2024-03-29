package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.RentPriceDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class PaymentCalculationService {
    private final RentPriceService rentPriceService;
    private final PaymentService paymentService;

    private RentPriceDTO rentPrice;

    public void setPaymentCalculations(PaymentDTO payment) {
        this.rentPrice = rentPriceService.getActual();

        paymentService.findPrevious(payment)
                .ifPresent(previousPayment -> setPaymentCalculations(payment, previousPayment));
    }

    private void setPaymentCalculations(PaymentDTO payment, PaymentDTO previousPayment) {
        payment.setGasQuantity(payment.getGas().subtract(previousPayment.getGas()));
        payment.setGasBill(multiplyAndScale(payment.getGasQuantity(), rentPrice.getGas()));

        payment.setWaterQuantity(payment.getWater().subtract(previousPayment.getWater()));
        payment.setWaterBill(multiplyAndScale(payment.getWaterQuantity(), rentPrice.getWater()));

        payment.setElectricityQuantity(payment.getElectricity().subtract(previousPayment.getElectricity()));
        payment.setElectricityBill(multiplyAndScale(payment.getElectricityQuantity(), rentPrice.getElectricity()));
    }

    private BigDecimal multiplyAndScale(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(2, RoundingMode.HALF_UP);
    }
}
