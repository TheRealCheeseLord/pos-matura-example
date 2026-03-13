package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeliveryAttemptRepositoryTest {

    @Autowired
    private DeliveryAttemptRepository deliveryAttemptRepository;

    @Test
    public void can_save() {
        var address = Address.builder()
                        .street("Musterstrasse")
                        .zip("1080")
                        .city("Mustermann")
                        .country("Austria")
                        .build();

        var depot = Depot.builder()
                .code("123456")
                .name("Depot 1")
                .address(address)
                .build();

        var driver = Driver.builder()
                .firstName("John")
                .lastName("Doe")
                .depot(depot)
                .build();

        var customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email(new Email("john@example.com"))
                .phone("123456789")
                .build();

        var shipment = Shipment.builder()
                .sender(customer)
                .trackingNumber("123")
                .recipientName("Kunger")
                .recipientAddress(address)
                .weightKg(new BigDecimal(12))
                .status(DeliveryStatus.Created)
                .currentDepot(depot)
                .assignedDriver(driver)
                .build();

        var deliveryAttempt = DeliveryAttempt.builder()
                .shipment(shipment)
                .driver(driver)
                .attemptedAt(LocalDateTime.now())
                .success(true)
                .build();

        var saved = deliveryAttemptRepository.saveAndFlush(deliveryAttempt);

        assertThat(saved).isNotNull();
    }
}
