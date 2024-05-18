import java.util.*;
public class Task10 {
    public static void changeArr() {
        System.out.println("\n ---- task 10 ----");

        int[] binaryArr = {1, 0, 0, 1,};
        System.out.println("Array before change - " + Arrays.toString(binaryArr));

        for(int i = 0; i < binaryArr.length; i++) {
            binaryArr[i] = binaryArr[i] == 0 ? 1 : 0;
        }

        System.out.println("Array after change - " + Arrays.toString(binaryArr));

    }
}
