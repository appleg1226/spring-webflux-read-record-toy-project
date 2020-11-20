package com.chong.userRecord.repository;

import com.chong.userRecord.domain.ReadRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReadRecordRepository extends ReactiveMongoRepository<ReadRecord, String> {
    Flux<ReadRecord> findAllByUserId(String userId);
    Flux<ReadRecord> findAllByUserIdAndContentId(String userId, String contentId);
}
