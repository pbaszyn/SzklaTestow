import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class vatServiceTest {
    private VatService vatService;
    private Product product;
    private VatProvider vatProvider;

    @Test
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = generateProduct("10", "boots");
        Mockito.when(vatProvider.getDefVat()).thenReturn(new BigDecimal("0.23"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
    }

    @Test
    void shouldReturnGrossPrice4GivenVat() throws VatValueOutOfBounds {
        //given
        product = generateProduct("20", "clothes");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("0.08"));

        //when
        BigDecimal result = vatService.getGrossPrise4GivenVat(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product));
    }

    @Test(expectedExceptions = VatValueOutOfBounds.class)
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() throws VatValueOutOfBounds {
        //given
        product = generateProduct("30", "lamps");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("1"));


        //then
        vatService.getGrossPrise4GivenVat(product);
    }

    @Test(expectedExceptions = VatValueOutOfBounds.class)
    void shouldThrowExceptionWhenVATisLowerThanZERO() throws VatValueOutOfBounds {
        //given
        product = generateProduct("30", "lamps");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("-0.5"));


        //then
        vatService.getGrossPrise4GivenVat(product);
    }

    private Product generateProduct(String netPrice, String type){
        return new Product(UUID.randomUUID(),new BigDecimal(netPrice),type);
    }

    @BeforeMethod
    private void setUpVatService() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }
}
