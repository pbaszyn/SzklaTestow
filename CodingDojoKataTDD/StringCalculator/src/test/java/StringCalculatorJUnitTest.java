import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorJUnitTest {

    @Test
    public void shouldReturn0ForEmptyString() {
        //given
        StringCalculator calculator = new StringCalculator();

        //when
        String result = calculator.calculate("");

        //then
        assertEquals(result,"0");
    }

    @Test
    void shouldReturnNumberForNumberGiven() {
        //given
        StringCalculator calculator = new StringCalculator();
        //when
        String result = calculator.calculate("5");

        //then
        assertEquals(result, "5");
    }
}
