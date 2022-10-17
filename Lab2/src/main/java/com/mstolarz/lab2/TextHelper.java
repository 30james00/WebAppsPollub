package com.mstolarz.lab2;

public class TextHelper {
    public static boolean isEmptyString(String[] texts) {
        for (String text : texts
        ) {
            if ((text == null) || (text.trim().equals("")))
                return true;
        }
        return false;
    }

    public static String changeCommaToDot(String text) {
        return text.replace(",", ".");
    }
}
