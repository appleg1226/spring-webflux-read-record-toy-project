package com.chong.userRecord.routing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecordRouter {

    @Bean
    public RouterFunction<ServerResponse> routeByRecord(RecordHandler handler){
        return nest(path("/record"),
                route(GET("/{userId}"), handler::getAllRecords)
                .andRoute(GET("/{userId}/{contentId}"), handler::getContentRecords)
                .andRoute(POST("/save").and(accept(MediaType.APPLICATION_JSON)), handler::addRecord)
        );
    }
}
