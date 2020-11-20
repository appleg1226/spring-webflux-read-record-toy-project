package com.chong.userRecord.service;

import com.chong.userRecord.domain.User;
import com.chong.userRecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> findById(String id){
        return userRepository.findById(id);
    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    public Mono<User> save(User user){
        return userRepository.save(user);
    }

    public Mono<Void> delete(String id){
        return userRepository.deleteById(id);
    }

    public Mono<User> update(User user){
        return userRepository.save(user);
    }
}
