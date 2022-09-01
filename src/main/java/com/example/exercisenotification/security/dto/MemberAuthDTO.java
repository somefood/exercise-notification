package com.example.exercisenotification.security.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class MemberAuthDTO extends User {

    private String username;

    private String password;

    public MemberAuthDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
