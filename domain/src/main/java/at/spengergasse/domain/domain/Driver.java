package at.spengergasse.domain.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "driver")
public class Driver extends Person{

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "depot",  foreignKey = @ForeignKey(name = "FK__depot__2__driver"))
    private Depot depot;
}
