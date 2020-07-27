import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class vatServiceTest {
    private VatService vatService;
    private Product product;

    @Test
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = new Product("bouts", new BigDecimal("10"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
    }

    @Test
    void shouldReturnGrossPrice4GivenVat() throws ToHighVatValueException {
        //given
        product = new Product("potatoes", new BigDecimal("20"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product,new BigDecimal("0.08")));
    }

    @Test(expectedExceptions = ToHighVatValueException.class)
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() throws ToHighVatValueException {
        //given
        product = new Product("potatoes", new BigDecimal("20"));

        //then
        vatService.getGrossPrise4GivenVat(product,BigDecimal.ONE);
    }

    @BeforeMethod
    void setUp() {
        vatService = new VatService();
    }
}
