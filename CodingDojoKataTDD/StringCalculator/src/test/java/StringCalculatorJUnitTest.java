import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorJUnitTest {

    StringCalculator calculator;

    @Test
    public void shouldReturn0ForEmptyString() {
        //given

        //when
        String result = calculator.add("");

        //then
        assertEquals("0.0",result);
    }

    @Test
    void shouldReturnSumOfMultipleNumbersWithFractionPartGiven() {
        //given

        //when
        String result = calculator.add("2.5,5.3,10,30.7,15");

        //then
        assertEquals("63.5",result);
    }

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }
}
