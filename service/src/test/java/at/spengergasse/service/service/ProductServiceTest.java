package at.spengergasse.service.service;

import at.spengergasse.service.domain.*;
import at.spengergasse.service.persistence.ProductRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        assumeThat(productService).isNotNull();
        assumeThat(productRepository).isNotNull();
    }

    @Test
    public void can_get_revenue_of_product() {
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product(
                123L,
                "product-123",
                "product-description",
                12000L,
                mock(Category.class),
                List.of(
                        new PreorderItem(
                                123L,
                                mock(Preorder.class),
                                mock(Product.class),
                                10,
                                1500L
                        ),
                        new PreorderItem(
                                456L,
                                mock(Preorder.class),
                                mock(Product.class),
                                10,
                                1500L
                        )
                )
        )));

        var product = productService.getRevenueOfProduct(123L);

        assertThat(product).isNotNull();
        assertThat(product).isNotEmpty();
        assertThat(product.get().RevenueInCents()).isEqualTo(1500L * 10 * 2);
    }
}
