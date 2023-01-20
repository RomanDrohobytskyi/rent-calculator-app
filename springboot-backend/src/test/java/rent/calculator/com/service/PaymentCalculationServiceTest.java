package rent.calculator.com.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import rent.calculator.com.model.dto.PaymentDTO;

import java.math.BigDecimal;
import java.util.Optional;

import static mock.MockPayments.*;
import static mock.MockPrices.mockActualRentPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}
