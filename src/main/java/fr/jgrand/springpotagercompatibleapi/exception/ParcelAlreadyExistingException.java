package fr.jgrand.springpotagercompatibleapi.exception;

public class ParcelAlreadyExistingException extends ApiException {
    public ParcelAlreadyExistingException(String message) {
        super(message);
    }
}
