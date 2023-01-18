package az.bakhishli.organization.ms.config;

import az.bakhishli.common.security.UserRole;
import az.bakhishli.common.security.auth.services.JwtService;
import az.bakhishli.common.security.auth.services.TokenAuthService;
import az.bakhishli.common.security.config.BaseSecurityConfig;
import az.bakhishli.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static az.bakhishli.common.security.UserRole.ROLE_ORGANIZATION_ADMIN;

@Slf4j
@EnableWebSecurity
@ComponentScan("az.bakhishli.common.security")
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends BaseSecurityConfig {

    public SecurityConfiguration(SecurityProperties securityProperties, JwtService jwtService) {
        super(securityProperties, List.of(new TokenAuthService(jwtService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/organization/**/").permitAll();
//        http.authorizeRequests()
//                .antMatchers("/organization/create").authenticated();
        http.authorizeRequests()
                .antMatchers("/organization/create")
                .access(authorities(ROLE_ORGANIZATION_ADMIN))
                .antMatchers("/employee/create")
                .access(authorities(ROLE_ORGANIZATION_ADMIN));
        super.configure(http);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
