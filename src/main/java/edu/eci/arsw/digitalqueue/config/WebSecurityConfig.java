package edu.eci.arsw.digitalqueue.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .requiresChannel()
                .anyRequest()
                .requiresSecure()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/queues/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/queues").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/queues/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/queues/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/queues/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/turns/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/turns").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/turns/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/turns/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/turns/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/attentionPoints/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/attentionPoints").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/attentionPoints/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/attentionPoints/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/attentionPoints/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable();
    }
}