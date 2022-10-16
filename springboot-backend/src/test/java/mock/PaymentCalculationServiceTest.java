package mock;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import rent.calculator.com.model.dto.PaymentDTO;
import rent.calculator.com.model.dto.RentPriceDTO;
import rent.calculator.com.service.PaymentCalculationService;
import rent.calculator.com.service.PaymentService;
import rent.calculator.com.service.RentPriceService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static rent.calculator.com.model.dto.PaymentDTO.builder;

@ExtendWith(MockitoExtension.class)
public class PaymentCalculationServiceTest {

    @InjectMocks
    PaymentCalculationService paymentCalculationService;
    @Mock
    RentPriceService rentPriceService;
    @Mock
    PaymentService paymentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCalculateActual() {
        // given
        PaymentDTO previousPayment = mockPreviousPayment();
        PaymentDTO actualPayment = mockActualPayment();
        when(paymentService.findPrevious(any())).thenReturn(Optional.of(previousPayment));
        when(rentPriceService.getActual()).thenReturn(mockActualRentPrice());

        //when
        this.paymentCalculationService.setPaymentCalculations(actualPayment);

        // then
        assertNotNull(actualPayment);

        assertEquals(actualPayment.getWaterBill(), BigDecimal.valueOf(111.68));
        assertEquals(actualPayment.getWaterQuantity(), BigDecimal.valueOf(3.97));

        assertEquals(actualPayment.getElectricityBill(), BigDecimal.valueOf(55.08));
        assertEquals(actualPayment.getElectricityQuantity(), BigDecimal.valueOf(54.0));

        assertEquals(actualPayment.getGasBill(), BigDecimal.valueOf(52.67));
        assertEquals(actualPayment.getGasQuantity(), BigDecimal.valueOf(7.06));
    }

    @Test
    public void shouldCalculatePrevious() {
        // given
        PaymentDTO previousPayment = mockPreviousPreviousPayment();
        PaymentDTO actualPayment = mockPreviousPayment();
        when(paymentService.findPrevious(any())).thenReturn(Optional.of(previousPayment));
        when(rentPriceService.getActual()).thenReturn(mockActualRentPrice());

        //when
        this.paymentCalculationService.setPaymentCalculations(actualPayment);

        // then
        assertNotNull(actualPayment);

        assertEquals(actualPayment.getWaterBill(), BigDecimal.valueOf(183.69));
        assertEquals(actualPayment.getWaterQuantity(), BigDecimal.valueOf(6.53));

        assertEquals(actualPayment.getElectricityBill(), BigDecimal.valueOf(90.88));
        assertEquals(actualPayment.getElectricityQuantity(), BigDecimal.valueOf(89.1));

        assertEquals(actualPayment.getGasBill(), BigDecimal.valueOf(87.06));
        assertEquals(actualPayment.getGasQuantity(), BigDecimal.valueOf(11.67));
    }

    private PaymentDTO mockActualPayment() {
        return builder()
                .water(BigDecimal.valueOf(1081.08))
                .electricity(BigDecimal.valueOf(427.5))
                .gas(BigDecimal.valueOf(775.7))
                .build();
    }

    private PaymentDTO mockPreviousPayment() {
        return builder()
                .water(BigDecimal.valueOf(1077.11))
                .electricity(BigDecimal.valueOf(373.5))
                .gas(BigDecimal.valueOf(768.64))
                .build();
    }

    private PaymentDTO mockPreviousPreviousPayment() {
        return builder()
                .water(BigDecimal.valueOf(1070.58))
                .electricity(BigDecimal.valueOf(284.4))
                .gas(BigDecimal.valueOf(756.97))
                .build();
    }

    private RentPriceDTO mockActualRentPrice() {
        return RentPriceDTO.builder()
                .actual(true)
                .electricity(BigDecimal.valueOf(1.02))
                .gas(BigDecimal.valueOf(7.46))
                .water(BigDecimal.valueOf(28.13))
                .rent(BigDecimal.valueOf(1800.00))
                .build();
    }

}
