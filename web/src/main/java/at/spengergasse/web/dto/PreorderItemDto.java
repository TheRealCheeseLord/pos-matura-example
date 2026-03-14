package at.spengergasse.web.dto;

public record PreorderItemDto(
        String productName,
        int quantity,
        Long unitPriceInCents
) {}
