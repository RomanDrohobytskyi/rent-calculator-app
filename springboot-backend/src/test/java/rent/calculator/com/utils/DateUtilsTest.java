package rent.calculator.com.utils;

import org.junit.Test;
import rent.calculator.com.model.dto.PaymentDTO;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static rent.calculator.com.utils.DateUtils.currentMonth;

public class DateUtilsTest {

    @Test
    public void monthShouldBePresent() {
        //when
        String month = currentMonth(mockPayment());

        //then
        assertThat(month).isEqualTo("grudzień");
    }

    private PaymentDTO mockPayment() {
        return PaymentDTO.builder()
                .paymentDate(LocalDate.of(2023, Month.DECEMBER, 1))
                .build();
    }

}
