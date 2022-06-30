package org.margareeta.linkshortener.authenticationservice.component;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
@ComponentScan(basePackages = "org.margareeta.linkshortener.common")
public class JwtBuilderImpl implements JwtBuilder{
    private final String AUTHORITIES= "authorities";
    private final SecretKey secretKey;
    @Value("${jwtExpirationMinutes}")
    private Long expirationMinutes;



    @Override
    public String buildJwt(UserDetails details) {
        return Jwts.builder()
                .setSubject(details.getUsername())
                .setIssuedAt(new Date())
                //.setExpiration(new Date(ZonedDateTime.of(LocalDateTime.now().plusMinutes(expirationMinutes), ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(expirationMinutes)))
                .claim(AUTHORITIES, details.getAuthorities())
                .signWith(secretKey)
                .compact();
    }
}
