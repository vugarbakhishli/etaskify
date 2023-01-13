package az.bakhishli.user.ms.service.impl;

import az.bakhishli.user.ms.domain.Authority;
import az.bakhishli.user.ms.domain.User;
import az.bakhishli.user.ms.domain.VerificationToken;
import az.bakhishli.user.ms.exceptions.EmailAlreadyUsedException;
import az.bakhishli.user.ms.exceptions.TokenNotFoundException;
import az.bakhishli.user.ms.repository.AuthorityRepository;
import az.bakhishli.user.ms.repository.UserRepository;
import az.bakhishli.user.ms.repository.VerificationTokenRepository;
import az.bakhishli.user.ms.service.UserService;
import az.bakhishli.user.ms.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.ms.service.dto.auth.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import static az.bakhishli.common.security.UserRole.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ModelMapper mapper;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void register(RegistrationDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getEmail());
                });
        User user = createUserEntity(dto);
        userRepository.save(user);
    }

    @Override
    public void activateAccount(ActivateAccountDto dto) {
        VerificationToken verificationToken = verificationTokenRepository
                .findByToken(dto.getToken())
                .orElseThrow(TokenNotFoundException::new);
        User user = verificationToken.getUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }


    private User createUserEntity(RegistrationDto dto) {
        User user = mapper.map(dto, User.class);
        Authority authority = Authority.builder()
                .authority(ROLE_USER.toString())
                .build();
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

}
