package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Category(
        @Id Long id,
        String name,
        List<Product> products
) {}
