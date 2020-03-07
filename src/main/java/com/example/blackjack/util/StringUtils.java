package com.example.blackjack.util;

public class StringUtils {

    public static boolean isBlank(final String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String toLowerCase(final String text) {
        if (text == null) {
            return "";
        }
        return text.toLowerCase();
    }
}
