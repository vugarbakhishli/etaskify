package az.bakhishli.organization.ms.service.impl;

import az.bakhishli.common.dto.user.UserResponseDto;
import az.bakhishli.organization.ms.client.UserMsClient;
import az.bakhishli.organization.ms.domain.Organization;
import az.bakhishli.organization.ms.repository.OrganizationRepository;
import az.bakhishli.organization.ms.service.OrganizationService;
import az.bakhishli.organization.ms.service.dto.CreateOrganizationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;
    private final UserMsClient userMsClient;


    @Override
    public void create(CreateOrganizationRequestDto dto) {
        UserResponseDto userDto = userMsClient.getCurrentUser();
        Organization organization = Organization.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .userId(userDto.getId())
                .build();
        repository.save(organization);
    }
}
