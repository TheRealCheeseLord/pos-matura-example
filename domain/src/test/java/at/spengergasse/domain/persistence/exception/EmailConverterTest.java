package at.spengergasse.domain.persistence.exception;

import at.spengergasse.domain.domain.Email;
import at.spengergasse.domain.persistence.converter.EmailConverter;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmailConverterTest {

    private final EmailConverter emailConverter = new EmailConverter();

    @Test
    void can_map_to_db() {
        String value = "test@example.com";
        assertThat(emailConverter.convertToDatabaseColumn(new Email(value))).isEqualTo(value);
        assertThat(emailConverter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void can_map_from_db() {
        String value = "test@example.com";
        assertThat(emailConverter.convertToEntityAttribute(value)).isEqualTo(new Email(value));
        assertThat(emailConverter.convertToEntityAttribute(null)).isNull();
    }

}
