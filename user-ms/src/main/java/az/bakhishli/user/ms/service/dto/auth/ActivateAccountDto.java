package az.bakhishli.user.ms.service.dto.auth;

import az.bakhishli.user.ms.config.PasswordConstants;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString(exclude = "password")
public class ActivateAccountDto {

    @NotNull
    @Size(min = PasswordConstants.PASSWORD_MIN_LENGTH, max = PasswordConstants.PASSWORD_MAX_LENGTH)
    private String password;

    private String token;
}
