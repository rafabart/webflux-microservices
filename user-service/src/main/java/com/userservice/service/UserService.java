package com.userservice.service;

import com.userservice.dto.UserDto;
import com.userservice.repository.UserRepository;
import com.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Flux<UserDto> all() {
        return this.userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }


    public Mono<UserDto> getById(Integer id) {
        return this.userRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<UserDto> create(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<UserDto> update(Integer id, Mono<UserDto> userDtoMono) {
        return this.userRepository.findById(id)
                .flatMap(user -> userDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(entity -> entity.setId(id)))
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<Void> deleteById(Integer id) {
        return this.userRepository.deleteById(id);
    }
}
