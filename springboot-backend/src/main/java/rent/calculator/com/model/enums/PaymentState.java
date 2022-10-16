package rent.calculator.com.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentState {
    NEW (1),
    ARCHIVE (-1);

    private int stateId;
}
