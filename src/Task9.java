public class Task9 {
    public static boolean isLeapYear(int year) {
        System.out.println("\n ---- task 9 ----");

        boolean isLeap = false;

        if (year % 4 == 0) {
            if (year % 100 != 0) {
                isLeap = true;
            } else if(year % 400 == 0) {
                isLeap = true;
            }
        }

        return isLeap;
    }
}
