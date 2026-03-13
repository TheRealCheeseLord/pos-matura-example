package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Customer(
        @Id Long id,
        Customer customer,
        String code,
        Long totalAmountInCents,
        List<PreorderItem> preorderItems
) {}
