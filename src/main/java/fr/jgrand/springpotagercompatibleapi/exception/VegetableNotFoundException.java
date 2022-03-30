package fr.jgrand.springpotagercompatibleapi.exception;

public class VegetableNotFoundException extends ApiException {
    public VegetableNotFoundException(String message) {
        super(message);
    }
}
