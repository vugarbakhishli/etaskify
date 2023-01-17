package az.bakhishli.organization.ms.web.rest;

import az.bakhishli.organization.ms.service.OrganizationService;
import az.bakhishli.organization.ms.service.dto.CreateOrganizationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateOrganizationRequestDto dto) {
        organizationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
