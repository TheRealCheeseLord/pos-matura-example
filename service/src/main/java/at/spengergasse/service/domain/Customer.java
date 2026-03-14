package at.spengergasse.service.domain;

import java.util.List;

public record Customer(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        List<Preorder> preorders
) {}
