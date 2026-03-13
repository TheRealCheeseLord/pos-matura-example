package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Address;
import at.spengergasse.domain.domain.Depot;
import at.spengergasse.domain.domain.Driver;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

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

        var driver = Driver.builder()
                .firstName("John")
                .lastName("Doe")
                .depot(depot)
                .build();

        var saved = driverRepository.saveAndFlush(driver);

        assertThat(saved).isNotNull();
    }
}
