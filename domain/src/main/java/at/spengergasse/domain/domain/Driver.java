package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "driver")
public class Driver {

    @EmbeddedId
    private DriverId employeeNo;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Depot depot;

    public record DriverId(@GeneratedValue Long id) {}
}
