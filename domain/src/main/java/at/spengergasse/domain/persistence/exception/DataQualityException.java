package at.spengergasse.domain.persistence.exception;

public class DataQualityException extends RuntimeException {
    public DataQualityException(String message) {
        super(message);
    }

    public static <T> DataQualityException forInvalidEnumLiteral(Class<? extends Enum> enumClass, T faultyValue) {
        String valueString = String.valueOf(faultyValue);
        String message = "Detected faulty value \"%s\" in conversion of \"%s\".".formatted(valueString, enumClass.getSimpleName());
        return new DataQualityException(message);
    }
}
