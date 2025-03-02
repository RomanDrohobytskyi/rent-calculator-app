package rent.calculator.com.payment.exception;

import lombok.Getter;

@Getter
public class PaymentUpdateException extends RuntimeException {
    private final static String NOT_EXIST_MESSAGE = "Could not update payment because payment with ID: %s does not exist.";
    private final String message;


    public PaymentUpdateException(Long paymentId) {
        super(String.format(NOT_EXIST_MESSAGE, paymentId));
        this.message = String.format(NOT_EXIST_MESSAGE, paymentId);
    }
}
