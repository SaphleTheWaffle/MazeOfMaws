package utils;

public class StringUtils {

    public static final String SEPARATOR = "\n\n";

    private StringUtils() {}

    public static String italics(String s) {
        return "*" + s + "*";
    }

    public static String bold(String s) {
        return italics(italics(s));
    }

    public static String boldItalics(String s) {
        return bold(italics(s));
    }

    public static String underline(String s) {
        return "__" + s + "__";
    }
}
