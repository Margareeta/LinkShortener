package org.daisy.repository;

import org.daisy.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkShortenerRepositiory extends JpaRepository<LinkEntity, String> {
}
