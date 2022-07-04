package ru.chel.SRMPlayGround.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String REALM_NAME = "Maxim";

    public static final String ADMIN_ROLE = "ADMIN";

    @Autowired
    public void configureGlobalSec(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("титова")
                .password(passwordEncoder().encode("работа22")).roles(ADMIN_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login")
                    .permitAll()
                .antMatchers("/api/**")
                    .hasAnyRole(ADMIN_ROLE)
                .antMatchers("/api/**")
                    .hasRole(ADMIN_ROLE)
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
                .and()
                    .httpBasic()
                    .realmName(REALM_NAME)
                    .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
