package az.bakhishli.user.ms.service;

import az.bakhishli.common.dto.user.CreateOrganizationUserDto;
import az.bakhishli.user.ms.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.ms.service.dto.auth.RegistrationDto;
import az.bakhishli.common.dto.user.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    void register(RegistrationDto registrationDto);
    void activateAccount(ActivateAccountDto dto);

    UserResponseDto getCurrentUserLogin();

    UserResponseDto createOrganizationUser(CreateOrganizationUserDto dto);
}
