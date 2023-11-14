package edu.uchicago.gerber._07streams.E19_6;

import java.util.Currency;

public class CurrencyUtil {
    public static void printSortedCurrencyDisplayNames() {
        Currency.getAvailableCurrencies().stream()
                .map(Currency::getDisplayName)
                .sorted()
                .forEach(System.out::println);
    }
}
