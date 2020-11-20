package com.chong.userRecord.routing;

import com.chong.userRecord.domain.ReadRecord;
import com.chong.userRecord.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class RecordHandler {

    private final RecordService recordService;

    public Mono<ServerResponse> getAllRecords(ServerRequest request){
        String userId = request.pathVariable("userId");
        Flux<ReadRecord> result = recordService.findAllRecordsByUserId(userId);

        return result.collectList()
                .flatMap(list ->{
                    if(!list.isEmpty()){
                        return ServerResponse.ok().body(result, ReadRecord.class);
                    } else{
                        return ServerResponse.badRequest().build();
                    }
                });
    }

    public Mono<ServerResponse> getContentRecords(ServerRequest request){
        String userId = request.pathVariable("userId");
        String contentId = request.pathVariable("contentId");
        return ServerResponse.ok().body(recordService.findContentRecordsByUserId(userId, contentId), ReadRecord.class)
                .onErrorResume(e -> Mono.just("error" + e.getMessage())
                        .flatMap(s -> ServerResponse.notFound().build())
                );
    }

    public Mono<ServerResponse> addRecord(ServerRequest request){
        return request.bodyToMono(ReadRecord.class)
                .flatMap(readRecord -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(recordService.saveRecord(readRecord), ReadRecord.class)
                ).onErrorResume(e -> Mono.just("error" + e.getMessage())
                    .flatMap(s -> ServerResponse.notFound().build())
                );
    }
}
