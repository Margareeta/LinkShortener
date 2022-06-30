package org.margareeta.linkshortener.authenticationservice.component;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtBuilder {
    String buildJwt(UserDetails details);
}
