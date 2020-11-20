package com.chong.userRecord.service;

import com.chong.userRecord.domain.User;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@Log
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void addAndReadTest(){
        userService.save(User.dummyOf("1")).block();

        Mono<User> result = userService.findById("1");
        StepVerifier
                .create(result)
                .consumeNextWith(user -> {
                    log.info("" + user);
                }).expectComplete()
                .verify();
    }

    @Test
    void deleteTest(){
        userService.save(User.dummyOf("1")).block();
        userService.save(User.dummyOf("2")).block();
        userService.save(User.dummyOf("3")).block();

        userService.delete("1").block();

        Flux<User> result = userService.findAll();
        StepVerifier.create(result)
                .expectNext(User.dummyOf("2"), User.dummyOf("3"))
                .expectComplete()
                .verify();
    }

    @Test
    void updateTest(){
        User dummyUser = User.dummyOf("1");
        userService.save(dummyUser).block();

        dummyUser.setName("chong");
        userService.update(dummyUser).block();

        Mono<User> result = userService.findById("1");
        StepVerifier.create(result)
                .expectNext(dummyUser)
                .expectComplete()
                .verify();
    }

    @Test
    void massiveDataTest(){
        Flux.range(0, 100)
                .flatMap(num -> userService.save(User.dummyOf(String.valueOf(num))))
                .subscribe(user -> {
                    log.info(""+user);
                });

    }

}