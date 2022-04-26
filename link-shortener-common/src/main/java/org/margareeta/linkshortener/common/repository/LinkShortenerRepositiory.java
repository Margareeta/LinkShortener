package org.margareeta.linkshortener.common.repository;

import org.margareeta.linkshortener.common.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkShortenerRepositiory extends JpaRepository<LinkEntity, String> {
}
