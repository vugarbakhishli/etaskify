package az.bakhishli.user.ms.exceptions;

import az.bakhishli.common.exception.NotFoundException;

import java.io.Serial;

public class TokenNotFoundException extends NotFoundException {

    public static final String TOKEN_NOT_FOUND = "Token not found";

    @Serial
    private static final long serialVersionUID = 1465946412164612L;

    public TokenNotFoundException() {
        super(TOKEN_NOT_FOUND);
    }
}
