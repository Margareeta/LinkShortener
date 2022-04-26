package org.margareeta.linkshortener.common.model.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margareeta.linkshortener.common.converter.RolePkConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePK implements Serializable {
    @Convert(converter = RolePkConverter.class)
    @Column(name = "role_id")
    private RoleEnum roleId;
}
