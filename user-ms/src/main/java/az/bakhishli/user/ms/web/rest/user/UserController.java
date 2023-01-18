package az.bakhishli.user.ms.web.rest.user;

import az.bakhishli.common.dto.user.CreateOrganizationUserDto;
import az.bakhishli.user.ms.service.UserService;
import az.bakhishli.common.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/current-user")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUserLogin());
    }

    @PostMapping("/create-organization-user")
    public ResponseEntity<UserResponseDto> createOrganizationUser(@RequestBody CreateOrganizationUserDto dto) {
        return ResponseEntity.ok(userService.createOrganizationUser(dto));
    }

}
