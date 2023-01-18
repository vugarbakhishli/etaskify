package az.bakhishli.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrganizationUserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
