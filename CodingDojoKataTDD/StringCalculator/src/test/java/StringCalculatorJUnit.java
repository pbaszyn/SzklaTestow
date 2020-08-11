import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorJUnit {

    @Test
    public void shouldReturn0ForEmptyString() {
        StringCalculator calculator = new StringCalculator();

        String result = calculator.calculate("");

        Assertions.assertEquals(result,"0");
    }


}
