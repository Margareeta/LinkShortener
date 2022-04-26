package org.margareeta.linkshortener.common.converter;

import org.margareeta.linkshortener.common.model.role.RoleEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RolePkConverter implements AttributeConverter<RoleEnum,String> {
    @Override
    public String convertToDatabaseColumn(RoleEnum roleEnum) {
        return roleEnum.getRoleName();
    }

    @Override
    public RoleEnum convertToEntityAttribute(String dbData) {
        return RoleEnum.findByRoleName(dbData);
    }
}
