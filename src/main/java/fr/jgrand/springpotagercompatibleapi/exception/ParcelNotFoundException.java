package fr.jgrand.springpotagercompatibleapi.exception;

public class ParcelNotFoundException extends ApiException {
    public ParcelNotFoundException(String message) {
        super(message);
    }
}
