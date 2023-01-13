package az.bakhishli.common.exception;

import java.io.Serial;

public class InvalidStateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidStateException(String message) {
        super(message);
    }
}
