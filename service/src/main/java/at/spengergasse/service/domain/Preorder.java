package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public record Preorder(
        @Id Long id,
        Customer customer,
        String code,
        LocalDateTime placedAt,
        Long totalAmountInCents,
        List<PreorderItem> preorderItems
) {}
