package utils;

import java.util.ArrayList;
import java.util.List;

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

    public static String listify(List<String> s) {
        if (s == null || s.size() < 1) {
            return "";
        }
        if (s.size() == 1) {
            return s.get(0);
        }

        List<String> list = new ArrayList<>(s);
        list.add(list.size() - 1, "and");
        String delimiter = list.size() > 3 ? ", " : " ";
        String res = String.join(delimiter, list);
        int lastComma = res.lastIndexOf(',');
        if (lastComma > 0) {
            res = res.substring(0, lastComma) + res.substring(lastComma + 1);
        }
        return res;
    }
}
