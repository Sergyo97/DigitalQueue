package edu.eci.arsw.digitalqueue.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN, SERVICE_MANAGER, AGENT;

    @Override
    public String getAuthority() {
        return name();
    }

}
