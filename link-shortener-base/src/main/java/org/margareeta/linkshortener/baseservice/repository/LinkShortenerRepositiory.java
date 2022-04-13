package org.margareeta.linkshortener.baseservice.repository;

import org.margareeta.linkshortener.baseservice.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkShortenerRepositiory extends JpaRepository<LinkEntity, String> {
}
