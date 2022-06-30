package org.margareeta.linkshortener.authenticationservice.service;


import org.margareeta.linkshortener.authenticationservice.dto.CredentialsDto;

public interface JwtService {
    String getJwt(CredentialsDto creds);
}
