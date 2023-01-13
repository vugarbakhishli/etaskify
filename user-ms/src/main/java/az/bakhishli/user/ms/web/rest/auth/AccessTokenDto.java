package az.bakhishli.user.ms.web.rest.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessTokenDto {

    private String accessToken;
}
