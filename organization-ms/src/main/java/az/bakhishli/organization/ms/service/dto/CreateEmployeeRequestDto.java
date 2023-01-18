package az.bakhishli.organization.ms.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEmployeeRequestDto {
    private String name;
    private String surname;
    private String email;
}
