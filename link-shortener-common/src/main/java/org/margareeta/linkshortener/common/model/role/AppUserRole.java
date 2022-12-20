package org.margareeta.linkshortener.common.model.role;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRole {

    @EmbeddedId
    @Getter
    @Setter
    private RolePK rolePk;
    @Column(name = "description")
    private String description;
}

