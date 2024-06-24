package com.demo.command.service;

import com.demo.shared.base.BaseAggregate;

public interface EventSourcingHandler<T>{
    void save(BaseAggregate aggregate);
    T getById(String id);
}
