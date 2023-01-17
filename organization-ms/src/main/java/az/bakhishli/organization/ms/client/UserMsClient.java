package az.bakhishli.organization.ms.client;

import az.bakhishli.common.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "${client.user-ms.name}",
        url = "${client.user-ms.host}${client.user-ms.path}")
public interface UserMsClient {

    @GetMapping("/user/current-user")
    UserResponseDto getCurrentUser();

}
