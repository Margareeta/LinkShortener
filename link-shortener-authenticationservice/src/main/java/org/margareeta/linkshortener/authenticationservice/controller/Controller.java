package org.margareeta.linkshortener.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.margareeta.linkshortener.authenticationservice.dto.CredentialsDto;
import org.margareeta.linkshortener.authenticationservice.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final JwtService jwtService;
    @GetMapping("/api/v1/")
    public String ping(@RequestParam(name = "ping") String ping){
        return "pong";
    }

    @PostMapping("/api/v1/jwt")
    public String getJwt (@RequestBody CredentialsDto creds){
        return jwtService.getJwt(creds);
    }
}
