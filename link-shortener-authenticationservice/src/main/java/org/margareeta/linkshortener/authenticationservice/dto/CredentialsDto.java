package org.margareeta.linkshortener.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsDto {
    private final String login;
    private final String password;
}
