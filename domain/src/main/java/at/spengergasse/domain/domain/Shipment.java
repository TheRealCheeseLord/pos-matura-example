package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "shipment")
public class Shipment {

    @EmbeddedId
    private ShipmentId id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "shipment", foreignKey = @ForeignKey(name = "FK__customer__2__shipment"))
    private Customer sender;

    @Column(nullable = false, unique = true)
    private String trackingNumber;

    @Column(nullable = false)
    private String recipientName;

    @Column(nullable = false)
    @Embedded
    private Address recipientAddress;

    @Column(nullable = false)
    private BigDecimal weightKg;

    @Column(nullable = false)
    private DeliveryStatus status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "depot", foreignKey = @ForeignKey(name = "FK__depot__2__shipment"))
    private Depot currentDepot;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "driver", foreignKey = @ForeignKey(name = "FK__driver__2__shipment"))
    private Driver assignedDriver;

    @OneToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            mappedBy = "shipment"
    )
    @Builder.Default
    private List<DeliveryAttempt> deliveries = List.of();

    public record ShipmentId(@GeneratedValue Long id) {}
}
