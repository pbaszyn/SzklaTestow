import java.math.BigDecimal;
import java.math.MathContext;
import java.util.UUID;

public class Product {
    private UUID id;
    private BigDecimal netPrice;

    public Product(UUID id, BigDecimal netPrice) {
        this.id = id;
        this.netPrice = netPrice;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }
}
