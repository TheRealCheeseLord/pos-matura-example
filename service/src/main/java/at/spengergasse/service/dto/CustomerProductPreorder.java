package at.spengergasse.service.dto;

public record CustomerProductPreorder(
        Long productId,
        int quantity
) {}
