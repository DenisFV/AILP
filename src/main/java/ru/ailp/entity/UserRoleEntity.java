package ru.ailp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleEntity implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
