package ru.koleson.photousersapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.koleson.photousersapi.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress(environment.getProperty("api.gateway.ip"))
                .and().addFilter(getAuthentinticationFilter());
        http.headers().frameOptions().disable();
    }

    private AuthFilter getAuthentinticationFilter() throws Exception {
        AuthFilter authFilter = new AuthFilter(authenticationManager(), userService, environment);
        authFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
//        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
