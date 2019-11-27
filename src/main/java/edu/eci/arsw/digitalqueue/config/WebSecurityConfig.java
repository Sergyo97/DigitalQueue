package edu.eci.arsw.digitalqueue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .logout()
                .and()
                .requiresChannel()
                .anyRequest()
                .requiresSecure()
                .and()
                .csrf().disable()
                .cors().disable()
                .formLogin()
                .permitAll()
                .successHandler(authenticationSuccessHandler);

        http
                .authorizeRequests()
                .antMatchers("/", "/index.html").permitAll()
                .antMatchers("/dashboard.html").hasAnyAuthority("ADMIN", "SERVICE_MANAGER")
                .antMatchers("/users**").hasAuthority("ADMIN")
                .antMatchers("/services**").hasAnyAuthority("ADMIN", "SERVICE_MANAGER")
                .antMatchers("/attentionPoints**").hasAnyAuthority("ADMIN", "SERVICE_MANAGER")
                .antMatchers("/manageTurns.html").hasAuthority("AGENT");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}