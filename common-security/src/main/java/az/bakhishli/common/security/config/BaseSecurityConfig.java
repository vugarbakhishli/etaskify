package az.bakhishli.common.security.config;

import az.bakhishli.common.security.UserRole;
import az.bakhishli.common.security.auth.JwtAuthFilterConfigurerAdapter;
import az.bakhishli.common.security.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
public abstract class BaseSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ACTUATOR = "/actuator/**";
    private static final String SWAGGER2 = "/v2/api-docs";
    private static final String SWAGGER3 = "/v3/api-docs";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";

    private final SecurityProperties securityProperties;
    private final List<AuthService> authServices;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource());
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //Allow swagger and actuator
        http.authorizeRequests().antMatchers(ACTUATOR).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER2).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER3).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_UI).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_HTML).permitAll();

        http.authorizeRequests().antMatchers(ACTUATOR).permitAll();
        //Disallow all requests by default unless explicitly defined in submodules
        //http.authorizeRequests().anyRequest().access(authorities(ROLE_SUPER_USER.name(), ROLE_ADMIN.name()));
        // Apply AuthRequestFilter
        http.apply(new JwtAuthFilterConfigurerAdapter(authServices));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/v3/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/**", "/swagger-ui.html",
                "/webjars/**", "/csrf", "/");
    }

    protected String authority(String role) {
        return "hasAuthority('" + role + "')";
    }

    protected String authority(UserRole role) {
        return "hasAuthority('" + role.name() + "')";
    }

    protected String authorities(Object... roles) {
        StringJoiner joiner = new StringJoiner(" or ");
        for (Object role : roles) {
            if (role instanceof UserRole) {
                joiner.add(authority((UserRole) role));
            } else {
                joiner.add(authority(role.toString()));
            }
        }
        return joiner.toString();
    }

    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = securityProperties.getCors();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
