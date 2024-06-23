import exceptions.NegativeNumberException;

public class FactorialCalculator {
    public  int getFactorial(int f) {
        if (f < 0) {
            throw new NegativeNumberException("Число для рассчета факториала не может быть отрицательным");
        }

        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}
