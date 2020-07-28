import java.math.BigDecimal;

public class VatValueOutOfBounds extends Throwable {
    private String message;

    public VatValueOutOfBounds(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
