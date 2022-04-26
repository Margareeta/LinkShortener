package org.margareeta.linkshortener.common.repository;

import org.margareeta.linkshortener.common.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
