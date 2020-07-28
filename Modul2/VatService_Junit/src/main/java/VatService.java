import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {

    private static final MathContext m = new MathContext(4);
    private VatProvider vatProvider;
    private BigDecimal vatValue;

    public VatService(VatProvider vatProvider){
        this.vatProvider = vatProvider;
    }

    public BigDecimal getGrossPrice4DefVatValue(Product product) {
        vatValue = vatProvider.getDefVat();
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    public BigDecimal getGrossPrise4GivenVat(Product product) throws VatValueOutOfBounds {
        vatValue = vatProvider.getVat4ProductType(product.getType());
        if ((vatValue.compareTo(BigDecimal.ONE) > -1) || (vatValue.compareTo(BigDecimal.ZERO) < 0)){
            throw new VatValueOutOfBounds("VAT Value:" + vatValue + "it's out of bounds");
        }
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    private BigDecimal getGrossPrice(BigDecimal netPrice,BigDecimal vatValue) {
        return netPrice.multiply(vatValue.add(new BigDecimal("1"))).round(m);
    }
}