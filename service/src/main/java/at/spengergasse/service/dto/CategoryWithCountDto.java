package at.spengergasse.service.dto;

import at.spengergasse.service.domain.Category;
import jakarta.validation.constraints.NotNull;

public record CategoryWithCountDto(
        String categoryName,
        Long productCount
) {
    public CategoryWithCountDto(@NotNull Category category) {
        this (
                category.name(),
                (long) category.products().size()
        );
    }
}
