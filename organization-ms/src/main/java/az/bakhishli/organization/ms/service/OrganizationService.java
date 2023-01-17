package az.bakhishli.organization.ms.service;

import az.bakhishli.organization.ms.service.dto.CreateOrganizationRequestDto;

public interface OrganizationService {
    void create(CreateOrganizationRequestDto dto);
}
