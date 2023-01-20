package rent.calculator.com.utils;

import rent.calculator.com.model.dto.PaymentDTO;

import static java.time.format.TextStyle.FULL_STANDALONE;
import static rent.calculator.com.utils.CustomLocale.polishLocale;

public class DateUtils {
    public static String currentMonth(PaymentDTO payment) {
        return payment.getPaymentDate().getMonth().getDisplayName(FULL_STANDALONE, polishLocale());
    }
}
