package at.spengergasse.service.service;

import at.spengergasse.service.domain.*;
import at.spengergasse.service.dto.CustomerProductPreorder;
import at.spengergasse.service.persistence.CustomerRepository;
import at.spengergasse.service.persistence.ProductRepository;
import at.spengergasse.service.service.exception.OnlineStoreException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        assumeThat(customerService).isNotNull();
        assumeThat(customerRepository).isNotNull();
        assumeThat(productRepository).isNotNull();
    }

    @Test
    public void can_add_product() {
        Customer customer = mock(Customer.class);

        Product product1 = new Product(
                123L,
                "product-123",
                "product-123-description",
                12000L,
                mock(Category.class),
                List.of()
        );

        Product product2 = new Product(
                456L,
                "product-456",
                "product-456-description",
                15000L,
                mock(Category.class),
                List.of()
        );

        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer));
        when(customer.preorders()).thenReturn(new ArrayList<>());
        when(customerRepository.save(any())).thenReturn(customer);
        when(productRepository.findById(product1.id())).thenReturn(Optional.of(product1));
        when(productRepository.findById(product2.id())).thenReturn(Optional.of(product2));

        Preorder preorder = customerService.addPreorder(customer.id(), List.of(
                new CustomerProductPreorder(product1.id(), 5),
                new CustomerProductPreorder(product2.id(), 5)
        ));

        assertThat(preorder).isNotNull();
        assertThat(preorder.customer()).isEqualTo(customer);
        assertThat(preorder.placedAt()).isNotNull();
        assertThat(preorder.totalAmountInCents()).isEqualTo(5 * 12000 + 5 * 15000);
        assertThat(preorder.preorderItems().size()).isEqualTo(2);
    }

    @Test
    public void add_product_throws_invalid_customer() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.addPreorder(123L, List.of()))
                .isInstanceOf(OnlineStoreException.class)
                .hasMessage("Invalid CustomerId.");
    }


    @Test
    public void add_product_throws_empty_preorders() {
        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(mock(Customer.class)));

        assertThatThrownBy(() -> customerService.addPreorder(123L, List.of()))
                .isInstanceOf(OnlineStoreException.class)
                .hasMessage("Empty Preorders.");
    }
}
