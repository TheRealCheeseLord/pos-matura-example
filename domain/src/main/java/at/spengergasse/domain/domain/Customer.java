package at.spengergasse.domain.domain;

import at.spengergasse.domain.persistence.converter.EmailConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @Convert(converter = EmailConverter.class)
    private Email email;
    @Column(nullable = false)
    private String phone;
    @ElementCollection()
    @CollectionTable(
            name = "customer_addresses",
            joinColumns = {
                    @JoinColumn(name = "customer", foreignKey = @ForeignKey(name = "customer_addresses__2__customer"))
            }
    )
    private List<Address> address;

    public record CustomerId(@GeneratedValue Long id) {}
}
