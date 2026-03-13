package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

public record PreorderItem(
        @Id Long id,
        Preorder preorder,
        Product product,
        int quantity,
        Long unitPriceInCents
) {}
