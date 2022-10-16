package rent.calculator.com.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMessage extends BaseEntity {
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String title;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String description;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String totalMedia;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String water;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String gas;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String electricity;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String total;
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String regards;
    private int version;
    private boolean actual;
}
