import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VatService {



    private static final Logger logger = Logger.getLogger(VatService.class.getName());
    private static final MathContext m = new MathContext(4);
    private VatProvider vatProvider;
    private BigDecimal vatValue;

    public VatService(VatProvider vatProvider){
        this.vatProvider = vatProvider;
    }

    public BigDecimal getGrossPrice4DefVatValue(Product product) {
        vatValue = vatProvider.getDefVat();
        logger.info("Called getGrossPrice4DefVatValue, Product: " + product.getNetPrice() + ", " + product.getType() + ", Vat:" + vatValue);
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    public BigDecimal getGrossPrise4GivenVat(Product product) throws VatValueOutOfBounds {
        vatValue = vatProvider.getVat4ProductType(product.getType());
        if ((vatValue.compareTo(BigDecimal.ONE) > -1) || (vatValue.compareTo(BigDecimal.ZERO) < 0)){
            logger.warning("Called getGrossPrise4GivenVat, Product: " + product.getNetPrice() + ", " + product.getType() + ", Vat Value:" + vatValue + " - it's out of bounds");
            throw new VatValueOutOfBounds("VAT Value:" + vatValue + "it's out of bounds");
        }
        logger.info("Called getGrossPrise4GivenVat, Product: " + product.getNetPrice() + ", " + product.getType() + ", Vat:" + vatValue);
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    private BigDecimal getGrossPrice(BigDecimal netPrice,BigDecimal vatValue) {
        logger.info("Called getGrossPrice, netPrice: " + netPrice + ", Vat%" + vatValue);
        return netPrice.multiply(vatValue.add(new BigDecimal("1"))).round(m);
    }
}