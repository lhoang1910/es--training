package com.demo.flow.common;

import com.demo.domain.event.user.UserCreatedEvent;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserCreatedEventConverter extends JsonMessageConverter {

    public UserCreatedEventConverter() {
        super();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);

        typeMapper.addTrustedPackages("com.demo.domain.event.user");

        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("UserCreatedEvent", UserCreatedEvent.class);
        typeMapper.setIdClassMapping(idClassMapping);

        this.setTypeMapper(typeMapper);
    }
}
