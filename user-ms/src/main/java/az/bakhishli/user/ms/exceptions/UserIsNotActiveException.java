package az.bakhishli.user.ms.exceptions;

import az.bakhishli.common.exception.InvalidStateException;

import java.io.Serial;

public class UserIsNotActiveException extends InvalidStateException {

    @Serial
    private static final long serialVersionUID = 58432132465811L;

    public UserIsNotActiveException() {
        super("The user is not active. Please activate your account");
    }
}
