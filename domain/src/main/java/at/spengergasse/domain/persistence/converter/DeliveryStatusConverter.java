package at.spengergasse.domain.persistence.converter;

import at.spengergasse.domain.domain.DeliveryStatus;
import at.spengergasse.domain.persistence.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeliveryStatusConverter implements AttributeConverter<DeliveryStatus, Character> {
    @Override
    public Character convertToDatabaseColumn(DeliveryStatus deliveryStatus) {
        return switch (deliveryStatus) {
            case Created -> 'C';
            case InTransit -> 'I';
            case OutForDelivery ->  'O';
            case Delivered -> 'D';
            case Failed -> 'F';
            case Returned -> 'R';
            case null -> null;
        };
    }

    @Override
    public DeliveryStatus convertToEntityAttribute(Character character) {
        return switch (character) {
            case 'C' -> DeliveryStatus.Created;
            case 'I' -> DeliveryStatus.InTransit;
            case 'O' -> DeliveryStatus.OutForDelivery;
            case 'D' -> DeliveryStatus.Delivered;
            case 'F' -> DeliveryStatus.Failed;
            case 'R' -> DeliveryStatus.Returned;
            case null -> null;
            default -> throw DataQualityException.forInvalidEnumLiteral(DeliveryStatus.class, character);
        };
    }
}
