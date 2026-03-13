package at.spengergasse.service.service;

import at.spengergasse.service.domain.Category;
import at.spengergasse.service.domain.Product;
import at.spengergasse.service.dto.CategoryWithCountDto;
import at.spengergasse.service.persistence.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        assumeThat(categoryService).isNotNull();
        assumeThat(categoryRepository).isNotNull();
    }

    @Test
    public void can_get_all_categories_with_count() {
        when(categoryRepository.findAll()).thenReturn(List.of(
                new Category(
                        123L,
                        "category-123",
                        List.of(
                                mock(Product.class),
                                mock(Product.class),
                                mock(Product.class)
                        )
                ),
                new Category(
                        456L,
                        "category-456",
                        List.of(
                                mock(Product.class),
                                mock(Product.class),
                                mock(Product.class),
                                mock(Product.class),
                                mock(Product.class)
                        )
                )
        ));

        var categoriesWithCount = categoryService.getCategoriesWithProductCounts();

        assertThat(categoriesWithCount).isNotEmpty();
        assertThat(categoriesWithCount).hasSize(2);
        assertThat(categoriesWithCount).extracting(CategoryWithCountDto::productCount).hasSizeGreaterThan(0);
    }
}
