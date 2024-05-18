import java.util.*;
public class Task12 {
    public static void mulArrayElements() {
        System.out.println("\n ---- task 12 ----");

        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Array before multiplication - " + Arrays.toString(array));

        for(int i = 0; i < array.length; i++) {
            if (array[i] < 6) array[i] *= 2;
        }

        System.out.println("Array after multiplication - " + Arrays.toString(array));
    }
}
