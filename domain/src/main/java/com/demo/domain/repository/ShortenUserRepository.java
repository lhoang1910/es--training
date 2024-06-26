package com.demo.domain.repository;

import com.demo.domain.entity.ShortenUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenUserRepository extends JpaRepository<ShortenUserEntity, String> {
}
