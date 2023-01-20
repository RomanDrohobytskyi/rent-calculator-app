package rent.calculator.com.utils;

import java.util.Locale;

import static java.util.Objects.isNull;

public class CustomLocale {
    private static Locale polishLocale;

    public static Locale polishLocale() {
        if (isNull(polishLocale)) {
            polishLocale = new Locale("pl", "PL");
        }
        return polishLocale;
    }
}
