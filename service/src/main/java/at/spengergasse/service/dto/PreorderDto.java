package at.spengergasse.service.dto;

import at.spengergasse.service.domain.Preorder;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PreorderDto(
        Long customerId,
        String customerName,
        String preorderCode,
        LocalDateTime preorderPlacedAt,
        Long preorderTotalAmountInCents
) {
    public PreorderDto(@NotNull Preorder preorder) {
        this (
                preorder.id(),
                "%s-%s".formatted(
                        preorder.customer().firstname(),
                        preorder.customer().lastname()
                ),
                preorder.code(),
                preorder.placedAt(),
                preorder.totalAmountInCents()
        );
    }
}
