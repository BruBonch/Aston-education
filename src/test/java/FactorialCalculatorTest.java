import exceptions.NegativeNumberException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class FactorialCalculatorTest {
    FactorialCalculator factorialCalculator;

    @DataProvider
    public static Object[] getNumbers() {
        return new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    @BeforeClass
    public void setUp() {
        factorialCalculator = new FactorialCalculator();
    }

    @Test
    public void factorialFromZero() {
        int expected = 1;
        int actual = factorialCalculator.getFactorial(0);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = NegativeNumberException.class,
            expectedExceptionsMessageRegExp = "Число для рассчета факториала не может быть отрицательным")
    public void factorialFromNegativeNumber() {
        factorialCalculator.getFactorial(-3);
    }

    @Test(dataProvider = "getNumbers")
    public void factorialFromPositiveNumber(Integer number) {

    }
}
