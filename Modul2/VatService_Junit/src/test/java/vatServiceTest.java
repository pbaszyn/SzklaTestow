import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class vatServiceTest {
    private VatService vatService;
    private Product product;


    @Test
    @DisplayName("Should return gross price for default VAT value")
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = generateProduct("10");

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
    }

    @Test
    @DisplayName("Should return gross price for 8% VAT value")
    void shouldReturnGrossPrice4GivenVat() throws VatValueOutOfBounds {
        //given
        product = generateProduct("20");

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product,new BigDecimal("0.08")));
    }

    @Test
    @DisplayName("Should throw Excepion when VAT value is Equal or Bigger than ONE")
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() {
        //given
        product = generateProduct("30");

        //then
        assertThrows(VatValueOutOfBounds.class, ()->{
            vatService.getGrossPrise4GivenVat(product,BigDecimal.ONE);
        });
    }

    @Test
    @DisplayName("Should throw Excepion when VAT value is lower than 0")
    void shouldThrowExceptionWhenVATisLowerThanZERO() {
        //given
        product = generateProduct("30");

        //then
        assertThrows(VatValueOutOfBounds.class, ()->{
            vatService.getGrossPrise4GivenVat(product,new BigDecimal("-0.5"));
        });
    }

    private Product generateProduct(String netPrice){
        return new Product(UUID.randomUUID(),new BigDecimal(netPrice));
    }

    @BeforeEach
    void setUp() {
        vatService = new VatService();
    }
}
