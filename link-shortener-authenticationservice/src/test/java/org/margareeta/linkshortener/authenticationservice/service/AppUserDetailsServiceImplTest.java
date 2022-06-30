package org.margareeta.linkshortener.authenticationservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.margareeta.linkshortener.authenticationservice.service.impl.AppUserDetailsService;
import org.margareeta.linkshortener.common.model.AppUser;
import org.margareeta.linkshortener.common.repository.AppUserRepository;
import org.margareeta.linkshortener.common.repository.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

class AppUserDetailsServiceImplTest extends BaseIntegrationTest {

    private final AppUserDetailsService service;
    private final TestDataProvider provider;
    private final AppUserRepository repository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDetailsServiceImplTest(AppUserDetailsService service, TestDataProvider provider, AppUserRepository repository, AppUserRoleRepository appUserRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.service = service;
        this.provider = provider;
        this.repository = repository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void shouldLoadUserByUsername(){
        AppUser builtUser = provider.getBuiltUser();
        appUserRoleRepository.save(builtUser.getRoles().stream().findAny().get());
        repository.save(builtUser);
        AppUser foundUser = (AppUser)service.loadUserByUsername(builtUser.getUsername());
        Collection<? extends GrantedAuthority> authorities = foundUser.getAuthorities();

        Assertions.assertEquals(1,authorities.size());
        Assertions.assertEquals(builtUser, foundUser);

        repository.deleteById(builtUser.getUsername());
    }


    @Test
    void shouldThrowUserNotFoundException(){
        AppUser builtUser = provider.getBuiltUser();

        Assertions.assertThrows(UsernameNotFoundException.class,
                ()->service.loadUserByUsername(builtUser.getUsername()));

    }
    @Test
    void scipherPassword(){
        System.out.println(passwordEncoder.encode("12345A"));
        System.out.println(passwordEncoder.encode("54321G"));
    }
}
