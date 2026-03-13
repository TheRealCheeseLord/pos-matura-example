package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Address;
import at.spengergasse.domain.domain.Customer;
import at.spengergasse.domain.domain.Depot;
import at.spengergasse.domain.domain.Email;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DepotRepositoryTest {

    @Autowired
    private DepotRepository depotRepository;

    @Test
    public void can_save() {
        var depot = Depot.builder()
                .code("123456")
                .name("Depot 1")
                .address(Address.builder()
                        .street("Musterstrasse")
                        .zip("1080")
                        .city("Mustermann")
                        .country("Austria")
                        .build())
                .build();

        var saved  = depotRepository.saveAndFlush(depot);

        assertThat(saved).isNotNull();
    }
}
