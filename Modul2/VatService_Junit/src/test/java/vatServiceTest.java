import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class vatServiceTest {
    private VatService vatService;
    private Product product;
    private VatProvider vatProvider;

    @Test
    @DisplayName("Should return gross price for default VAT value")
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = generateProduct("10", "boots");
        Mockito.when(vatProvider.getDefVat()).thenReturn(new BigDecimal("0.23"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
        Mockito.verify(vatProvider, Mockito.times(1)).getDefVat();
        Mockito.verify(vatProvider, Mockito.never()).getVat4ProductType(product.getType());
    }

    @Test
    @DisplayName("Should return gross price for 8% VAT value")
    void shouldReturnGrossPrice4GivenVat() throws VatValueOutOfBounds {
        //given
        product = generateProduct("20", "clothes");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("0.08"));

        //when
        BigDecimal result = vatService.getGrossPrise4GivenVat(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product));
        Mockito.verify(vatProvider, Mockito.atLeastOnce()).getVat4ProductType("clothes");
    }

    @Test
    @DisplayName("Should throw Excepion when VAT value is Equal or Bigger than ONE")
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() {
        //given
        product = generateProduct("30", "lamps");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("1"));

        //then
        assertThrows(VatValueOutOfBounds.class, ()->{
            vatService.getGrossPrise4GivenVat(product);
        });
        Mockito.verify(vatProvider, Mockito.atLeastOnce()).getVat4ProductType("lamps");

    }

    @Test
    @DisplayName("Should throw Excepion when VAT value is lower than 0")
    void shouldThrowExceptionWhenVATisLowerThanZERO() {
        //given
        product = generateProduct("30", "lamps");
        Mockito.when(vatProvider.getVat4ProductType(product.getType())).thenReturn(new BigDecimal("-0.5"));

        //then
        assertThrows(VatValueOutOfBounds.class, ()->{
            vatService.getGrossPrise4GivenVat(product);
        });
        Mockito.verify(vatProvider, Mockito.atLeastOnce()).getVat4ProductType("lamps");
    }

    private Product generateProduct(String netPrice, String type){
        return new Product(UUID.randomUUID(),new BigDecimal(netPrice),type);
    }

    @BeforeEach
    private void setUpVatService() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }
}
