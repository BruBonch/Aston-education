import exceptions.NegativeNumberException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactorialCalculatorTest {
    FactorialCalculator factorialCalculator;

    @BeforeClass
    public void setUp() {
        factorialCalculator = new FactorialCalculator();
    }

    @DataProvider
    public static Object[] getNumbers() {
        return new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    @Test
    public void factorialFromZero() {
        int expected = 1;
        int actual = factorialCalculator.getFactorial(0);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = NegativeNumberException.class,
            expectedExceptionsMessageRegExp = "Число для рассчета факториала не может быть отрицательным")
    public void factorialFromNegativeNumber() {
        factorialCalculator.getFactorial(-3);
    }

    @Test(dataProvider = "getNumbers")
    public void factorialFromPositiveNumber(Integer number) {
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
