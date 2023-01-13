package az.bakhishli.common.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private final JwtProperties jwtProperties = new JwtProperties();

    private final CorsConfiguration cors = new CorsConfiguration();

    @Getter
    @Setter
    public static class JwtProperties {

        private String secret;
        private long tokenValidityInSeconds;
        private long tokenValidityInSecondsForRememberMe;
    }

}
