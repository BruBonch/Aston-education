import java.util.*;
public class Task11 {
    public static void fillArray() {
        System.out.println("\n ---- task 10 ----");

        int[] intArr = new int[100];
        System.out.println("Array before fill - " + Arrays.toString(intArr));

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = i + 1;
        }

        System.out.println("Array after fill - " + Arrays.toString(intArr));
    }
}
