import java.math.BigDecimal;
import java.math.MathContext;

public class Product {
    private String id;
    private BigDecimal netPrice;

    public Product(String id, BigDecimal netPrice) {
        this.id = id;
        this.netPrice = netPrice;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }
}
