package lapr.project.shared.exceptions;

import java.io.IOException;

/**
 * This exception is used when a method finds invalid results after using its designated parameters.
 *
 */
public class InvalidDataException extends IOException {

    public InvalidDataException() {
        super("Invalid Parameters!");
    }

    public InvalidDataException(String s) {
        super(s);
    }
}
