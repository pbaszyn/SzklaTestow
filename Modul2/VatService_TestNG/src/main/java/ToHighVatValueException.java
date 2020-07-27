import java.math.BigDecimal;

public class ToHighVatValueException extends Throwable {
    private String message;

    public ToHighVatValueException(String message, BigDecimal vatValue) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
