import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorJUnitTest {

    @Test
    public void shouldReturn0ForEmptyString() {
        //given
        StringCalculator calculator = new StringCalculator();

        //when
        String result = calculator.add("");

        //then
        assertEquals("0",result);
    }

    @Test
    void shouldReturnNumberForNumberGiven() {
        //given
        StringCalculator calculator = new StringCalculator();
        //when
        String result = calculator.add("5");

        //then
        assertEquals("5",result);
    }

    @Test
    void shouldReturnSumOfMultipleNumbersGiven() {
        //given
        StringCalculator calculator = new StringCalculator();

        //when
        String result = calculator.add("2,5,10,30,15");

        //then
        assertEquals("62",result);
    }
}
