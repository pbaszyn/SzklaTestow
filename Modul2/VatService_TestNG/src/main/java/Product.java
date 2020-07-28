import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private BigDecimal netPrice;

    public String getType() {
        return type;
    }

    private String type;

    public Product(UUID id, BigDecimal netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }
}
