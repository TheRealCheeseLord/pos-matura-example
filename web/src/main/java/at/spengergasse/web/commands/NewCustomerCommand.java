package at.spengergasse.web.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record NewCustomerCommand(
        @Size(min = 1, max = 255) String firstName,
        @Size(min = 1, max = 255) String lastName,
        @Email String email,
        String phone
) {}
