package me.drefos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/register").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/processlogin").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll();
    }
}
