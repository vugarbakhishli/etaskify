package az.bakhishli.organization.ms.service.impl;

import az.bakhishli.common.dto.user.CreateOrganizationUserDto;
import az.bakhishli.common.dto.user.UserResponseDto;
import az.bakhishli.organization.ms.client.UserMsClient;
import az.bakhishli.organization.ms.domain.Employee;
import az.bakhishli.organization.ms.domain.Organization;
import az.bakhishli.organization.ms.repository.EmployeeRepository;
import az.bakhishli.organization.ms.repository.OrganizationRepository;
import az.bakhishli.organization.ms.service.EmployeeService;
import az.bakhishli.organization.ms.service.dto.CreateEmployeeRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final UserMsClient userMsClient;

    @Override
    @Transactional
    public void create(CreateOrganizationUserDto dto) {
        UserResponseDto userResponseDto = userMsClient.createOrganizationUser(dto);
        UserResponseDto currentUser = userMsClient.getCurrentUser();

        Organization organization =
                organizationRepository.findOrganizationByUserId(currentUser.getId())
                        .orElseThrow(() -> new RuntimeException("Organization not found!"));

        Employee employee = Employee.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .userId(userResponseDto.getId())
                .organization(organization)
                .build();
        employeeRepository.save(employee);
    }
}
