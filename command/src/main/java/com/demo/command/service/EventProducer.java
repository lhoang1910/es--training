package com.demo.command.service;

import com.demo.shared.base.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
