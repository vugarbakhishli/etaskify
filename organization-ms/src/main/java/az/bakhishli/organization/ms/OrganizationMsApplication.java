package az.bakhishli.organization.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrganizationMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationMsApplication.class, args);
    }
}
