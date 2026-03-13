package at.spengergasse.service.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Customer(
        @Id Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        List<Preorder> preorders
) {}
