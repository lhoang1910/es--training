package com.demo.command.service.impl;

import com.demo.command.entity.EventStoreEntity;
import com.demo.command.repository.EventStoreRepository;
import com.demo.command.service.EventProducer;
import com.demo.command.service.EventStore;
import com.demo.domain.aggregate.UserAggregate;
import com.demo.shared.base.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserEventStore implements EventStore {

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;


    @Override
    public void saveEvents(String aggregateIdentifier, Iterable<BaseEvent> events, int expectedVersion) {
        Optional<List<EventStoreEntity>> existEvents = eventStoreRepository.findByAggregateIdentifier(aggregateIdentifier);

        List<EventStoreEntity> listEvents = existEvents.get();

        if (expectedVersion != -1 && listEvents.get(listEvents.size() - 1).getVersion() != expectedVersion) {    // Không phải phiên bản mặc định
            throw new RuntimeException("event version is not valid");
        }

        int version = expectedVersion;

        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);
            EventStoreEntity eventStore = EventStoreEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateIdentifier)
                    .aggregateType(UserAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();

            EventStoreEntity persistedEvent = eventStoreRepository.save(eventStore);

            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }

        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateIdentifier) {

        Optional<List<EventStoreEntity>> existEvents = eventStoreRepository.findByAggregateIdentifier(aggregateIdentifier);
        if (existEvents.isEmpty()) {
            throw new NotFoundException("No event was not found");
        }

        List<EventStoreEntity> listEvents = existEvents.get();

        return listEvents.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }
}
