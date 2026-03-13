package at.spengergasse.service.dto;

import at.spengergasse.service.domain.PreorderItem;
import at.spengergasse.service.domain.Product;
import jakarta.validation.constraints.NotNull;

public record ProductWithRevenueDto(
   Long productId,
   String productName,
   Long RevenueInCents
) {
    public ProductWithRevenueDto(@NotNull Product product) {
        this (
                product.id(),
                product.name(),
                product.preorderItems().stream()
                        .mapToLong(PreorderItem::calculateRevenueInCents)
                        .sum()
        );
    }
}
