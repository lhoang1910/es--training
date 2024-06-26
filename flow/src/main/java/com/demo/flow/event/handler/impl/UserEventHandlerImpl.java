package com.demo.flow.event.handler.impl;

import com.demo.domain.entity.ShortenUserEntity;
import com.demo.domain.entity.UserEntity;
import com.demo.domain.event.user.UserCreatedEvent;
import com.demo.domain.event.user.UserDeletedEvent;
import com.demo.domain.event.user.UserUpdatedEvent;
import com.demo.domain.repository.ShortenUserRepository;
import com.demo.domain.repository.UserRepository;
import com.demo.flow.event.handler.UserEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShortenUserRepository shortenUserRepository;

    @Override
    public void on(UserCreatedEvent event) {
        UserEntity user = UserEntity.builder()
                .id(event.getId())
                .userCode(event.getUserCode())
                .username(event.getUsername())
                .password(event.getPassword())
                .role(event.getRole())
                .deleted(false)
                .build();
        userRepository.save(user);

        ShortenUserEntity shortenUserEntity = ShortenUserEntity.builder()
                .id(event.getId())
                .userCode(event.getUserCode())
                .role(event.getRole())
                .deleted(event.getIsDeleted())
                .build();
        shortenUserRepository.save(shortenUserEntity);
    }

    @Override
    public void on(UserUpdatedEvent event) {
        Optional<UserEntity> existUser = userRepository.findById(event.getId());
        UserEntity updateUser = existUser.get();

        updateUser.setUsername(event.getUsername());
        updateUser.setPassword(event.getPassword());
        updateUser.setRole(event.getRole());

        userRepository.save(updateUser);

        Optional<ShortenUserEntity> existShortenUser = shortenUserRepository.findById(event.getId());
        existShortenUser.get().setRole(event.getRole());
        shortenUserRepository.save(existShortenUser.get());
    }

    @Override
    public void on(UserDeletedEvent event) {
        Optional<UserEntity> existUser = userRepository.findById(event.getId());
        UserEntity beDeletedUser = existUser.get();
        beDeletedUser.setDeleted(true);

        userRepository.save(beDeletedUser);

        Optional<ShortenUserEntity> existShortenUser = shortenUserRepository.findById(event.getId());
        existShortenUser.get().setDeleted(true);

        shortenUserRepository.save(existShortenUser.get());
    }
}
