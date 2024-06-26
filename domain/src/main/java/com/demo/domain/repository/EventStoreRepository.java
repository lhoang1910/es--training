package com.demo.domain.repository;

import com.demo.domain.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    Optional<List<EventStoreEntity>> findByAggregateIdentifier(String aggregateIdentifier);
}
