package az.bakhishli.organization.ms.client;

import az.bakhishli.common.dto.user.CreateOrganizationUserDto;
import az.bakhishli.common.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "${client.user-ms.name}",
        url = "${client.user-ms.host}${client.user-ms.path}")
public interface UserMsClient {

    @GetMapping("/user/current-user")
    UserResponseDto getCurrentUser();

    @PostMapping("/user/create-organization-user")
    UserResponseDto createOrganizationUser(@RequestBody CreateOrganizationUserDto dto);

}
