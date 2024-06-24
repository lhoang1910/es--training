package com.demo.command.service;

import com.demo.shared.base.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateIdentifier, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateIdentifier);
}
