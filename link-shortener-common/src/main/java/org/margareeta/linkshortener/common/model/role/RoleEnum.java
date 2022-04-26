package org.margareeta.linkshortener.common.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUEST("ROLE_GUEST");

    private String roleName;

    public static RoleEnum findByRoleName(String roleName) {
        return Arrays.stream(values())
                .filter(roleEnum -> roleEnum.getRoleName().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no such role"));

    }

}
