import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    StringCalculator calculator;

    @Test
    void shouldReturnNumberWhenNumberGiven() {
        createCalculator();
        assertEquals(2, calculator.add("2"));
    }
    @Test
    void shouldReturnSumOfNumbersWhenNumbersGiven(){
        createCalculator();
        assertEquals(10, calculator.add("2,3,1,4"));
    }

    private void createCalculator() {
        calculator = new StringCalculator();
    }
}