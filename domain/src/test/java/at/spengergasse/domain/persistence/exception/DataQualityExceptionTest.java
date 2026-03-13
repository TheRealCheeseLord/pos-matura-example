package at.spengergasse.domain.persistence.exception;

import at.spengergasse.domain.domain.DeliveryStatus;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DataQualityExceptionTest {

    @Test
    public void for_invalid_enum_literal() {
        String faulty = "X";
        var exception = DataQualityException.forInvalidEnumLiteral(DeliveryStatus.class, faulty);

        assertThat(exception)
                .isInstanceOf(DataQualityException.class)
                .hasMessageStartingWith("Detected faulty value \"%s\" in conversion of \"%s\".".formatted(faulty, DeliveryStatus.class.getSimpleName()));
    }
}
