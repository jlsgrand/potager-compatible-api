package fr.jgrand.springpotagercompatibleapi.exception;

public class ParcelDimensionExceededException extends ApiException {
    public ParcelDimensionExceededException(String message) {
        super(message);
    }
}
