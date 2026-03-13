package at.spengergasse.domain.persistence.converter;

import at.spengergasse.domain.domain.DeliveryStatus;
import at.spengergasse.domain.persistence.exception.DataQualityException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeliveryStatusConverterTest {

    private final DeliveryStatusConverter converter = new DeliveryStatusConverter();

    @Test
    public void can_map_to_db() {
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.Created)).isEqualTo('C');
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.InTransit)).isEqualTo('I');
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.OutForDelivery)).isEqualTo('O');
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.Delivered)).isEqualTo('D');
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.Failed)).isEqualTo('F');
        assertThat(converter.convertToDatabaseColumn(DeliveryStatus.Returned)).isEqualTo('R');
    }

    @Test
    public void can_map_from_db() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
        assertThat(converter.convertToEntityAttribute('C')).isEqualTo(DeliveryStatus.Created);
        assertThat(converter.convertToEntityAttribute('I')).isEqualTo(DeliveryStatus.InTransit);
        assertThat(converter.convertToEntityAttribute('O')).isEqualTo(DeliveryStatus.OutForDelivery);
        assertThat(converter.convertToEntityAttribute('D')).isEqualTo(DeliveryStatus.Delivered);
        assertThat(converter.convertToEntityAttribute('F')).isEqualTo(DeliveryStatus.Failed);
        assertThat(converter.convertToEntityAttribute('R')).isEqualTo(DeliveryStatus.Returned);
    }

    @Test
    public void invalid_throws_dqe() {
        assertThatThrownBy(() -> converter.convertToEntityAttribute('?'))
            .isInstanceOf(DataQualityException.class);
    }

}
