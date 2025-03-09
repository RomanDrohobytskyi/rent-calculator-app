package rent.calculator.com.payment.exception;

import lombok.Getter;

@Getter
public class PaymentDeleteException extends RuntimeException {
    private final static String CAN_NOT_DELETE_MESSAGE = "Can not delete payment: %s";
    private final String message;


    public PaymentDeleteException(String message) {
        super(message);
        this.message = message;
    }

    public static PaymentDeleteException deletePaymentException(Long id) {
        return deletePaymentException(String.valueOf(id));
    }

    public static PaymentDeleteException deletePaymentException(String id) {
        return new PaymentDeleteException(String.format(CAN_NOT_DELETE_MESSAGE, id));
    }

}
