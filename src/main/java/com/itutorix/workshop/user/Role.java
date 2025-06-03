package com.itutorix.workshop.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.itutorix.workshop.user.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    PARTICIPANT(
            Set.of(
                    PARTICIPANT_READ,
                    PARTICIPANT_UPDATE,
                    PARTICIPANT_DELETE,
                    PARTICIPANT_CREATE
            )
    ),
    ORGANIZER(
            Set.of(
                    ORGANIZER_READ,
                    ORGANIZER_UPDATE,
                    ORGANIZER_DELETE,
                    ORGANIZER_CREATE
            )
    ),
    JURY(
            Set.of(
                    JURY_READ,
                    JURY_UPDATE,
                    JURY_DELETE,
                    JURY_CREATE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>(
                getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .toList()
        );

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
