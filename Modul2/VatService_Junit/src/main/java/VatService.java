import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {

    private static final BigDecimal defVatValue = new BigDecimal("0.23");
    private static final MathContext m = new MathContext(4);


    public BigDecimal getGrossPrice4DefVatValue(Product product) {
        return getGrossPrice(product.getNetPrice(), defVatValue);
    }

    public BigDecimal getGrossPrise4GivenVat(Product product, BigDecimal vatValue) throws ToHighVatValueException {
        if (vatValue.compareTo(BigDecimal.ONE) == 0){
            throw new ToHighVatValueException("VAT must be lower than 1",vatValue);
        }
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    private BigDecimal getGrossPrice(BigDecimal netPrice,BigDecimal vatValue) {
        return netPrice.multiply(vatValue.add(new BigDecimal("1"))).round(m);
    }
}