package at.spengergasse.service.service;

import at.spengergasse.service.domain.Category;
import at.spengergasse.service.dto.CategoryWithCountDto;
import at.spengergasse.service.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryWithCountDto> getCategoriesWithProductCounts() {
        return categoryRepository.findAll().stream()
                .map(CategoryWithCountDto::new)
                .toList();
    }
}
