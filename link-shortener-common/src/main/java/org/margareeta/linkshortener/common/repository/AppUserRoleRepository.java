package org.margareeta.linkshortener.common.repository;

import org.margareeta.linkshortener.common.model.role.AppUserRole;
import org.margareeta.linkshortener.common.model.role.RolePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole, RolePK> {
}
