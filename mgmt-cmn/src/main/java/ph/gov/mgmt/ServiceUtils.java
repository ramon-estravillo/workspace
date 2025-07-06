package ph.gov.mgmt;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class ServiceUtils {

    private static final Logger logger = Logger.getLogger("ServiceUtils");

    private ServiceUtils() {
        logger.info("Prevent from any initializations.");
    }

    public static boolean isGreaterThan(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0;
    }

    public static boolean isLesserThan(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) < 0;
    }

    public static boolean isEquals(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) == 0;
    }

    public static boolean isSameTag(char str1, char str2) {
        return str1 == str2;
    }

    public static boolean isSameTag(String str1, String str2) {
        return isSameTag(str1, str2, true);
    }

    public static boolean isSameTag(String str1, String str2, boolean caseSensitive) {
        return caseSensitive
                ? str1.equals(str2)
                : str1.equalsIgnoreCase(str2);
    }

}
