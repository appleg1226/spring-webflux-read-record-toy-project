package com.chong.userRecord.service;

import com.chong.userRecord.domain.Content;
import com.chong.userRecord.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public Mono<Content> add(Content content){
        return contentRepository.save(content);
    }

    public Mono<Content> findById(String contentId){
        return contentRepository.findById(contentId);
    }
}
