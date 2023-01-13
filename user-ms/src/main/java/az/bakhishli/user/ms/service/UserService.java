package az.bakhishli.user.ms.service;

import az.bakhishli.user.ms.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.ms.service.dto.auth.RegistrationDto;

public interface UserService {
    void register(RegistrationDto registrationDto);
    void activateAccount(ActivateAccountDto dto);

}
