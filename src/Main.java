import myExceptions.MyArrayDataException;
import myExceptions.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        String[][] testArr = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "g", "1", "1"},
                {"1", "1", "1", "1"},
        };

        try {
            printSumArraysElements(testArr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printSumArraysElements(String[][] array) {
        // не знал как сделать лучше. Решил сначала проверять что массив не зубчатый, потом уже с ним работать.
        // Так что сначала холостой обход на проверку длины, а потом попытки преобразования.

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i > 3 || j > 3) throw new MyArraySizeException("Массив должен быть 4х4");
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    String infoMessage = String.format(
                            "Символ - '%s', находящийся в %d-й ячейке %d-го массива, нельзя привести к типу int",
                            array[i][j], j + 1, i + 1
                    );
                    throw new MyArrayDataException(infoMessage);
                }

            }
        }

        System.out.println("Сумма массива = " + sum);
    }
}
