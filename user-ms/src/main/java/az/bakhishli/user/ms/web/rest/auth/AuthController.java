package az.bakhishli.user.ms.web.rest.auth;

import az.bakhishli.common.security.auth.services.JwtService;
import az.bakhishli.user.ms.domain.User;
import az.bakhishli.user.ms.exceptions.UserIsNotActiveException;
import az.bakhishli.user.ms.service.UserService;
import az.bakhishli.user.ms.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.ms.service.dto.auth.LoginDto;
import az.bakhishli.user.ms.service.dto.auth.RegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> authorize(@Validated @RequestBody LoginDto loginDto) {
        log.trace("Login request by user {}", loginDto.getEmail());
        log.trace("Authenticating user {}", loginDto.getEmail());
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.issueToken(authentication, ONE_DAY);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        return new ResponseEntity<>(new AccessTokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/activate-account")
    public ResponseEntity<Void> activateAccount(@RequestBody @Validated ActivateAccountDto dto) {
        log.trace("Sign up request with email {}", dto.getToken());
        userService.activateAccount(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegistrationDto dto) {
        log.trace("Sign up request with email {}", dto.getEmail());
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    private void checkIfAccountActive(User user) {
        if (!user.isEnabled()
                || !user.isAccountNonLocked()
                || !user.isAccountNonExpired()
                || !user.isCredentialsNonExpired()) {
            throw new UserIsNotActiveException();
        }
    }

}
