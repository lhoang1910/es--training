package com.demo.command.service.impl;

import com.demo.command.service.EventSourcingHandler;
import com.demo.command.service.EventStore;
import com.demo.domain.aggregate.UserAggregate;
import com.demo.shared.base.BaseAggregate;
import com.demo.shared.base.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserEventSourcingHandler implements EventSourcingHandler<UserAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(BaseAggregate aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public UserAggregate getById(String id) {
        UserAggregate aggregate = new UserAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (!events.isEmpty()){
            aggregate.replayEvents(events);
            Optional<Integer> latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

}
