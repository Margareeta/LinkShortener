package org.margareeta.linkshortener.baseservice.jwt;


import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.margareeta.linkshortener.common.config.SecretKeyConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.Jwts;

@Component
@RequiredArgsConstructor
@ComponentScan(basePackages = "org.margareeta.linkshortener.common")
public class JwtFilter extends OncePerRequestFilter {
    private final static String BEARER_PREFIX = "Bearer ";
    private final SecretKey secretKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(Strings.isNullOrEmpty(header) || !header.startsWith(BEARER_PREFIX)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String jwtToken = header.replace(BEARER_PREFIX, "");
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken);
        } catch (JwtException e){
            e.printStackTrace();
        }
    }
}
