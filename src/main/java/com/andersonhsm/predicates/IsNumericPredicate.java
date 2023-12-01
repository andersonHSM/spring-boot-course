package com.andersonhsm.predicates;

import java.util.function.Predicate;

public class IsNumericPredicate implements Predicate<String> {
    public static boolean isNumeric(String stringNumber) {
        if (stringNumber == null) return false;

        String normalizedNumber = stringNumber.replaceAll(",", ".");

        return normalizedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    @Override
    public boolean test(String s) {
        return isNumeric(s);
    }
}
