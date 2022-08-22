package rent.calculator.com.utils;

import rent.calculator.com.model.dto.PaymentDTO;

import java.time.format.TextStyle;
import java.util.Locale;

public class CommonUtils {

    public static String getMonth(PaymentDTO payment) {
        //DateFormatSymbols.getInstance(new Locale("pl", "PL")).getMonths();
        //TODO - TEST
        return payment.getPaymentDate().getMonth().getDisplayName(TextStyle.FULL, new Locale("pl", "PL"));
    }
}
