package org.margareeta.linkshortener.authenticationservice.service;

import org.margareeta.linkshortener.common.model.AppUser;
import org.margareeta.linkshortener.common.model.role.AppUserRole;
import org.margareeta.linkshortener.common.model.role.RoleEnum;
import org.margareeta.linkshortener.common.model.role.RolePK;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TestDataProvider {
    public AppUser getBuiltUser() {
        AppUser builtUser = AppUser.builder()
                .firstName("Mike")
                .lastName("Doe")
                .password("12345")
                .username("mikedoe")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .isEnabled(true)
                .roles(Set.of(new AppUserRole(new RolePK(RoleEnum.ROLE_ADMIN), "admin")))
                .build();
        return builtUser;

    }
}
