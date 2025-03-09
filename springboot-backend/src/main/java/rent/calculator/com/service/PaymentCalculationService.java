package rent.calculator.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.model.dto.UtilityBillDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentCalculationService {
    private final RentPriceService rentPriceService;
    private final PaymentService paymentService;

    private RentPriceDTO rentPrice;

    public void setPaymentCalculations(PaymentDTO payment) {
        this.rentPrice = rentPriceService.getActual();

        paymentService.findPrevious(payment)
                .map(previousPayment -> setPaymentCalculations(payment, previousPayment))
                .orElseGet(() -> mapPaymentWithoutPreviousPayment(payment));
    }

    private PaymentDTO mapPaymentWithoutPreviousPayment(PaymentDTO payment) {
        //TODO
        return payment;
    }

    private PaymentDTO setPaymentCalculations(PaymentDTO payment, PaymentDTO previousPayment) {
        BigDecimal totalCostOfUtilityBills = BigDecimal.ZERO;
        for (UtilityBillDTO utilityBill : payment.getUtilityBills()) {
            Optional<UtilityBillDTO> previousPaymentUtilityBill = previousPayment.findFirstUtilityBillByType(utilityBill.getUtilityType());

            previousPaymentUtilityBill.ifPresent(previousUtilityBill -> {
                utilityBill.setConsumption(utilityBill.getMeterState().subtract(previousUtilityBill.getMeterState()));
                utilityBill.setCost(multiplyAndScale(utilityBill.getConsumption(), getRentPriceByUtilityBillType(utilityBill)));
            });
            totalCostOfUtilityBills = totalCostOfUtilityBills.add(utilityBill.getCost());
        }
        payment.setTotal(totalCostOfUtilityBills);
        return payment;
    }

    private BigDecimal getRentPriceByUtilityBillType(UtilityBillDTO utilityBill) {
        return switch (utilityBill.getUtilityType()) {
            case GAS -> rentPrice.getGas();
            case ELECTRICITY -> rentPrice.getElectricity();
            case WATER -> rentPrice.getWater();
        };
    }

    private BigDecimal multiplyAndScale(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(2, RoundingMode.HALF_UP);
    }
}
