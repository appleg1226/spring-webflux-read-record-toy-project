package com.chong.userRecord.service;

import com.chong.userRecord.domain.ReadRecord;
import com.chong.userRecord.repository.ReadRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final ReadRecordRepository recordRepository;

    public Flux<ReadRecord> findAllRecordsByUserId(String userId){
        return recordRepository.findAllByUserId(userId);
    }

    public Flux<ReadRecord> findContentRecordsByUserId(String userId, String contentId){
        return recordRepository.findAllByUserIdAndContentId(userId, contentId)
                .switchIfEmpty(Mono.error(NoSuchElementException::new));
    }

    public Mono<ReadRecord> saveRecord(ReadRecord readRecord){
        return recordRepository.save(readRecord);
    }
}
