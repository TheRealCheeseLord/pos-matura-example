package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Product(
        @Id Long id,
        String name,
        String description,
        Long priceInCents,
        Category category,
        List<PreorderItem> preorderItems
) {}
