package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "customer")
public class Customer {

    @EmbeddedId
    private CustomerId id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Email email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    @Embedded
    private Address address;

    public record CustomerId(@GeneratedValue Long id) {}
}
