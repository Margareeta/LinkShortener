package org.margareeta.linkshortener.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.margareeta.linkshortener.authenticationservice.component.JwtBuilder;
import org.margareeta.linkshortener.authenticationservice.dto.CredentialsDto;
import org.margareeta.linkshortener.authenticationservice.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final AuthenticationManager manager;
    private final JwtBuilder builder;
    private final UserDetailsService service;

    @Override
    public String getJwt(CredentialsDto creds) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(creds.getLogin(),creds.getPassword()));
        return builder.buildJwt(service.loadUserByUsername(creds.getLogin()));
    }
}
