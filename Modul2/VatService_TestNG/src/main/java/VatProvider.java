import java.math.BigDecimal;

public interface VatProvider {

    BigDecimal getDefVat();

    BigDecimal getVat4ProductType(String type);
}
