package at.spengergasse.domain.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {
    public static final String VALIDATION_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final Pattern pattern = Pattern.compile(VALIDATION_REGEX);

    public Email {
        if (Objects.isNull(value)) throw new IllegalArgumentException("Value must not be null");
        if (!pattern.matcher(value).matches()) throw new IllegalArgumentException("Value must be valid according to pattern");
    }
}
