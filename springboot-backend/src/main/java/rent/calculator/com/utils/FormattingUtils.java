package rent.calculator.com.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormattingUtils {
    private final static String PAYMENT_PATTERN = "#.##";

    public static String format(BigDecimal toBeFormatted) {
        return new DecimalFormat(PAYMENT_PATTERN).format(toBeFormatted);
    }
}
