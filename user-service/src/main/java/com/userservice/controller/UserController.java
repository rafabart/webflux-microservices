package com.userservice.controller;

import com.userservice.dto.UserDto;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public Flux<UserDto> getAll() {
        return this.userService.all();
    }


    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> getById(@PathVariable final Integer id) {
        return this.userService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Mono<UserDto> create(@RequestBody final Mono<UserDto> dtoMono) {
        return this.userService.create(dtoMono);
    }


    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateById(@RequestBody final Mono<UserDto> dtoMono,
                                                    @PathVariable final Integer id) {
        return this.userService.update(id, dtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable final Integer id) {
        return this.userService.deleteById(id);
    }
}
