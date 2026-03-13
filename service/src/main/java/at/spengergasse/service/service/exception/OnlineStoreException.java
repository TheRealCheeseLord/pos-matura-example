package at.spengergasse.service.service.exception;

public class OnlineStoreException extends RuntimeException {
    public OnlineStoreException(String message) {
        super(message);
    }

    public static OnlineStoreException forInvalidCustomerId(Long customerId) {
        return new OnlineStoreException("Invalid CustomerId.");
    }

    public static OnlineStoreException forEmptyPreorders() {
        return new OnlineStoreException("Empty Preorders.");
    }
}
