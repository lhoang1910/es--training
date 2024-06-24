package com.demo.command.repository;

import com.demo.command.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    Optional<List<EventStoreEntity>> findByAggregateIdentifier(String aggregateIdentifier);
}
