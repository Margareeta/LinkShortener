package org.margareeta.linkshortener.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.margareeta.linkshortener.common.model.role.AppUserRole;
import org.margareeta.linkshortener.common.repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {
    @Id
    @Column(name = "username")//
    private String username;
    @Column(name = "password")//
    private String password;
    @Column(name = "first_name")//
    private String firstName;
    @Column(name = "last_name")//
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_user_app_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AppUserRole> roles;
    @Column(name = "is_account_non_expired")//
    private boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked")//
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired")//
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")//
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (AppUserRole role : roles)
            authorities.add(new SimpleGrantedAuthority(role.getRolePk().getRoleId().getRoleName()));
        return authorities;
    }   //todo: stream.map
}

