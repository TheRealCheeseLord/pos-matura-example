package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "depot")
public class Depot {

    @EmbeddedId
    private DepotId id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Address address;

    public record DepotId(@GeneratedValue Long id) {}
}
