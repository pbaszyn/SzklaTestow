import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {

    private static final BigDecimal defVatValue = new BigDecimal("0.23");
    private static final MathContext m = new MathContext(4);


    public BigDecimal getGrossPrice4DefVatValue(Product product) {
        return getGrossPrice(product.getNetPrice(), defVatValue);
    }

    public BigDecimal getGrossPrise4GivenVat(Product product, BigDecimal vatValue) throws VatValueOutOfBounds {
        if ((vatValue.compareTo(BigDecimal.ONE) > -1) || (vatValue.compareTo(BigDecimal.ZERO) < 0)){
            throw new VatValueOutOfBounds("VAT Value:" + vatValue + "it's out of bounds");
        }
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    private BigDecimal getGrossPrice(BigDecimal netPrice,BigDecimal vatValue) {
        return netPrice.multiply(vatValue.add(new BigDecimal("1"))).round(m);
    }
}