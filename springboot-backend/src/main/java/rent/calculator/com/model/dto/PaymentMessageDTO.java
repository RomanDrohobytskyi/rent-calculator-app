package rent.calculator.com.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMessageDTO {
    private Long id;
    private String title;
    private String description;
    private String totalMedia;
    private String water;
    private String gas;
    private String electricity;
    private String total;
    private String regards;
    private boolean actual;
}
