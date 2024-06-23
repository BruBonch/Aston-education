import exceptions.NegativeNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class FactorialCalculatorTest {
    public static FactorialCalculator factorialCalculator;
    @BeforeAll
    public static void setUp() {
        factorialCalculator = new FactorialCalculator();
    }
    @Test
    public void factorialFromZero() {
        int expected = 1;
        int actual = factorialCalculator.getFactorial(0);

        assertEquals(expected, actual);
    }

    @Test
    public void factorialFromNegativeNumber() {
        Exception exception = assertThrows(NegativeNumberException.class, () -> factorialCalculator.getFactorial(-4));

        String expectedMessage = "Число для рассчета факториала не может быть отрицательным";
        assertEquals(expectedMessage, exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void factorialFromPositiveNumber(int number) {
        int factorial = factorialCalculator.getFactorial(number);

        switch (number) {
            case 1:
                assertEquals(1, factorial);
                break;
            case 2:
                assertEquals(2, factorial);
                break;
            case 3:
                assertEquals(6, factorial);
                break;
            case 4:
                assertEquals(24, factorial);
                break;
            case 5:
                assertEquals(120, factorial);
                break;
            case 6:
                assertEquals(720, factorial);
                break;
            case 7:
                assertEquals(5040, factorial);
                break;
            case 8:
                assertEquals(40320, factorial);
                break;
            case 9:
                assertEquals(362880, factorial);
                break;
            case 10:
                assertEquals(3628800, factorial);
                break;
        }
    }
}

