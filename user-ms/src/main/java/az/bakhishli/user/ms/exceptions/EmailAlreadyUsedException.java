package az.bakhishli.user.ms.exceptions;

import az.bakhishli.common.exception.InvalidStateException;

import java.io.Serial;

public class EmailAlreadyUsedException extends InvalidStateException {

    public static final String EMAIL_ALREADY_USED = "Email \"%s\" already registered,try different email";

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String email) {
        super(String.format(EMAIL_ALREADY_USED, email));
    }
}
