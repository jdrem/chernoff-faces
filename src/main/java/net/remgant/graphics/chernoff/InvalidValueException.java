package net.remgant.graphics.chernoff;

public class InvalidValueException extends Exception {
    public InvalidValueException(String message) {
        super(String.format("Value for %s must be between 0 and 1", message));
    }
}
