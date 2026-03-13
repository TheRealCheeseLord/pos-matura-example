package at.spengergasse.domain.persistence.converter;

import at.spengergasse.domain.domain.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return Objects.isNull(email) ? null : email.value();
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        return Objects.isNull(s) ? null : new Email(s);
    }
}
