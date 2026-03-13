package at.spengergasse.domain.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmailTest {

    @Test
    public void can_create_valid_email() {
        Email email = new Email("test@example.com");

        assertThat(email).isNotNull();
    }

    @Test
    public void throws_on_null_email() {
        assertThatThrownBy(() -> new Email(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value must not be null");
    }

    @Test
    public void throws_on_invalid_email() {
        assertThatThrownBy(() -> new Email("x"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value must be valid according to pattern");
    }
}
