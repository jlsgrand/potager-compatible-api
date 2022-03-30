package fr.jgrand.springpotagercompatibleapi.exception;

public class VegetableAlreadyExisting extends ApiException {
    public VegetableAlreadyExisting(String message) {
        super(message);
    }
}
