package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "delivery_attempt")
public class DeliveryAttempt {

    @EmbeddedId
    private DeliveryAttemptId id;
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "shipment", foreignKey = @ForeignKey(name = "FK__delivery_attempt__2__shipment"))
    private Shipment shipment;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "driver", foreignKey = @ForeignKey(name = "FK__driver__2__delivery_attempt"))
    private Driver driver;
    @Column(nullable = false)
    private LocalDateTime attemptedAt;
    @Column(nullable = false)
    private boolean success;
    private String notes;

    public record DeliveryAttemptId(@GeneratedValue Long id) {}
}
