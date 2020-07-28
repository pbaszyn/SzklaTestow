import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class vatServiceTest {
    private VatService vatService;
    private Product product;

    @Test
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = generateProduct("10");

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
    }

    @Test
    void shouldReturnGrossPrice4GivenVat() throws VatValueOutOfBounds {
        //given
        product = generateProduct("20");

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product,new BigDecimal("0.08")));
    }

    @Test(expectedExceptions = VatValueOutOfBounds.class)
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() throws VatValueOutOfBounds {
        //given
        product = generateProduct("30");

        //then
        vatService.getGrossPrise4GivenVat(product,BigDecimal.ONE);
    }

    @Test(expectedExceptions = VatValueOutOfBounds.class)
    void shouldThrowExceptionWhenVATisLowerThanZERO() throws VatValueOutOfBounds {
        //given
        product = generateProduct("30");

        //then
        vatService.getGrossPrise4GivenVat(product,new BigDecimal("-0.5"));
    }
    private Product generateProduct(String netPrice){
        return new Product(UUID.randomUUID(),new BigDecimal(netPrice));
    }

    @BeforeMethod
    void setUp() {
        vatService = new VatService();
    }
}
