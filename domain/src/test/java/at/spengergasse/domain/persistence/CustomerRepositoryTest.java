package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Address;
import at.spengergasse.domain.domain.Customer;
import at.spengergasse.domain.domain.Email;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void can_save() {
        var customer = Customer.builder()
                .firstName("Max")
                .lastName("Mustermann")
                .email(new Email("max@example.com"))
                .phone("123")
                .address(
                    List.of(
                        Address.builder()
                        .street("Musterstrasse")
                        .zip("1080")
                        .city("Mustermann")
                        .country("Austria")
                        .build())
                    )
                .build();

        var saved  = customerRepository.saveAndFlush(customer);

        assertThat(saved).isNotNull();
    }
}
