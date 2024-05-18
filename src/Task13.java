import java.util.*;
public class Task13 {
    public static void changeDiagonals() {
        System.out.println("\n ---- task 13 ----");

        int[][] squareArray = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        System.out.println("Array before change");

        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray.length; j++) {
                System.out.print(squareArray[i][j] + "  ");
            }
            System.out.println();
        }
        // change array
        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray.length; j++) {
                if (i == j || j == squareArray[i].length - i - 1) squareArray[i][j] = 1;
            }
        }

        System.out.println("Array after change");

        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray.length; j++) {
                System.out.print(squareArray[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
