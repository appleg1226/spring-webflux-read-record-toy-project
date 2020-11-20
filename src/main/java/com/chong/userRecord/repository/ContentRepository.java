package com.chong.userRecord.repository;

import com.chong.userRecord.domain.Content;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ContentRepository extends ReactiveMongoRepository<Content, String> {
}