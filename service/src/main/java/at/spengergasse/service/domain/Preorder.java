package at.spengergasse.service.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Preorder(
        Long id,
        Customer customer,
        String code,
        LocalDateTime placedAt,
        Long totalAmountInCents,
        List<PreorderItem> preorderItems
) {}
