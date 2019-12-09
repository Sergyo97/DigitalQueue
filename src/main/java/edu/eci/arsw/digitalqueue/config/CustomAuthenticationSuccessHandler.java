package edu.eci.arsw.digitalqueue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.eci.arsw.digitalqueue.controller.UserController;
import edu.eci.arsw.digitalqueue.exception.UserNotFoundException;
import edu.eci.arsw.digitalqueue.model.User;
import edu.eci.arsw.digitalqueue.repository.AttentionPointRepository;
import edu.eci.arsw.digitalqueue.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AttentionPointRepository attentionPointRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("AGENT")) {
            User agent = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()));
            httpServletResponse.sendRedirect("manageTurns.html?id=" + attentionPointRepository.findByUser(agent).getId());
        }
        else httpServletResponse.sendRedirect("dashboard.html");

    }

}
