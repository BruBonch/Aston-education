package util;

public class Utilities {
    public static String getFloatingPointPaySum(String paySum) {
        return (paySum.contains(".")) ? paySum : paySum + ".00";
    }
}
