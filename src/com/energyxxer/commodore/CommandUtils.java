package com.energyxxer.commodore;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Class containing a series of utility methods and constants for common use in commands.
 * */
public final class CommandUtils {

    /**
     * String describing all the characters allowed in a string without the need of quotation marks.
     * */
    private static final String STRING_ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-+";

    /**
     * Escapes the given string's quotes and backslashes.
     *
     * @param str The string to be escaped.
     *
     * @return The escaped string.
     * */
    public static String escape(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    /**
     * Escapes and wraps the given string in quotes if not all its characters are allowed by commands in an
     * unquoted string. If all characters are allowed, nothing is changed.
     *
     * @param str The string to check and, if needed, quote.
     *
     * @return The given string, quoted, only if it contains a character not allowed in an unquoted string. Otherwise,
     * the returned string is the same as the original.
     * */
    public static String quoteIfNecessary(String str) {
        return (needsQuoting(str)) ? "\"" + escape(str) + "\"" : str;
    }

    /**
     * Returns whether this character contains any characters disallowed in unquoted strings.
     *
     * @return <code>true</code> if this character contains any characters, disallowed in unquoted strings,
     * <code>false</code> if all characters are allowed in unquoted strings.
     * */
    public static boolean needsQuoting(String str) {
        for(char c : str.toCharArray()) {
            if(!STRING_ALLOWED.contains(c + "")) return true;
        }
        return false;
    }

    /**
     * Converts the given number into its plain string representation. This method differs from
     * {@link Double#toString()} on two aspects:
     *
     * <ol>
     *     <li>Whole numbers will be displayed without the decimal places.</li>
     *     <li>Scientific notation will not be used for big nor small numbers.</li>
     * </ol>
     *
     * @param num The number to turn into a plain number string.
     * */
    public static String toString(double num) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        return df.format(num);
    }

    /**
     * CommandUtils should not be instantiated.
    */
    private CommandUtils() {
    }
}
