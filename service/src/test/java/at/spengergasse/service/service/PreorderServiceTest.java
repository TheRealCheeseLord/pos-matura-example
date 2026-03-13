package at.spengergasse.service.service;

import at.spengergasse.service.domain.Customer;
import at.spengergasse.service.domain.Preorder;
import at.spengergasse.service.persistence.PreorderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PreorderServiceTest {

    @InjectMocks
    private PreorderService preorderService;

    @Mock
    private PreorderRepository preorderRepository;

    @BeforeEach
    public void setup() {
        assumeThat(preorderService).isNotNull();
        assumeThat(preorderRepository).isNotNull();
    }

    @Test
    public void can_get_preorders_of_customer() {
        Customer customer = new Customer(
                123L,
                "Max",
                "Mustermann",
                "max@example.com",
                "123456789",
                List.of()
        );

        when(preorderRepository.findAllByCustomerId(any())).thenReturn(List.of(
                new Preorder(
                     123L,
                        customer,
                        "preorder-123",
                        LocalDateTime.now(),
                        45000L,
                        List.of()
                ),
                new Preorder(
                        456L,
                        customer,
                        "preorder-456",
                        LocalDateTime.now(),
                        123000L,
                        List.of()
                )
        ));

        var preorders = preorderService.getPreordersOfCustomer(customer.id());

        assertThat(preorders).isNotEmpty();
        assertThat(preorders).hasSize(2);
        assertThat(preorders.getFirst().customerName()).isEqualTo("Max-Mustermann");
    }
}
