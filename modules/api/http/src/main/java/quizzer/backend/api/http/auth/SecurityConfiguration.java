package quizzer.backend.api.http.auth;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import quizzer.backend.api.http.auth.encoders.Argon2Parameters;
import quizzer.backend.api.http.auth.userdetails.service.StoredUserDetailsPasswordService;
import quizzer.backend.api.http.auth.userdetails.service.StoredUserDetailsService;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class SecurityConfiguration {
    private static final String ARGON_2 = "argon2";

    @Bean
    @Profile("!dev")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").hasRole("USER"))
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    @Profile("dev")
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            Argon2Parameters parameters,
            StoredUserDetailsService userDetailsService,
            StoredUserDetailsPasswordService userDetailsPasswordService
    ) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder(parameters));
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setUserDetailsPasswordService(userDetailsPasswordService);
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(Argon2Parameters parameters) {
        var argon2 = new Argon2PasswordEncoder(
                parameters.getSaltLength(),
                parameters.getHashLength(),
                parameters.getParallelism(),
                parameters.getMemory(),
                parameters.getIterations()
        );
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(ARGON_2, argon2);
        encoders.put("noop", NoOpPasswordEncoder.getInstance()); //NOSONAR this is not the default password encoder
        return new DelegatingPasswordEncoder(ARGON_2, encoders);
    }
}
