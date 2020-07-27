import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class vatServiceTest {
    private VatService vatService;
    private Product product;


    @Test
    @DisplayName("Should return gross price for default VAT value")
    void shouldReturnGrossPriceWithDefaultVat() {
        //given
        product = new Product("bouts", new BigDecimal("10"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("12.30"),result);
    }

    @Test
    @DisplayName("Should return gross price for 8% VAT value")
    void shouldReturnGrossPrice4GivenVat() throws ToHighVatValueException {
        //given
        product = new Product("potatoes", new BigDecimal("20"));

        //when
        BigDecimal result = vatService.getGrossPrice4DefVatValue(product);

        //then
        assertEquals(new BigDecimal("21.60"),vatService.getGrossPrise4GivenVat(product,new BigDecimal("0.08")));
    }

    @Test
    @DisplayName("Should throw Excepion when VAT value is Equal or Bigger than ONE")
    void shouldThrowExceptionWhenVATisEqualOrBiggerThanONE() {
        //given
        product = new Product("potatoes", new BigDecimal("20"));

        //then
        assertThrows(ToHighVatValueException.class, ()->{
            vatService.getGrossPrise4GivenVat(product,BigDecimal.ONE);
        });
    }


    @BeforeEach
    void setUp() {
        vatService = new VatService();
    }
}
