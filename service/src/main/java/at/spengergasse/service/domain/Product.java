package at.spengergasse.service.domain;

import java.util.List;

public record Product(
        Long id,
        String name,
        String description,
        Long priceInCents,
        Category category,
        List<PreorderItem> preorderItems
) {}
