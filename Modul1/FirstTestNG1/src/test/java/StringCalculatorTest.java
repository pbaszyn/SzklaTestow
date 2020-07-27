import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringCalculatorTest {

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