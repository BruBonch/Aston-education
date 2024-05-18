import java.sql.SQLOutput;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Task1.printTreeWords();
        Task2.checkSumSign();
        Task3.printColor();
        Task4.compareNumbers();
        System.out.println(Task5.checkSum(1, 20));
        System.out.println(Task6.checkPositiveOrNegative(0));
        System.out.println(Task7.checkNegativeNum(23));
        Task8.repeatString("target string ", 2);
        System.out.println(Task9.isLeapYear(1900));
        Task10.changeArr();
        Task11.fillArray();
        Task12.mulArrayElements();
        Task13.changeDiagonals();
        System.out.println(Arrays.toString(Task14.fillArray(6, 42)));
    }
}
