package az.bakhishli.organization.ms.service;

import az.bakhishli.common.dto.user.CreateOrganizationUserDto;
import az.bakhishli.organization.ms.service.dto.CreateEmployeeRequestDto;

public interface EmployeeService {
    void create(CreateOrganizationUserDto dto);
}
