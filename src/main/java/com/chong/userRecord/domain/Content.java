package com.chong.userRecord.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Content {
    @Id
    private String contentId;
    private ContentType contentType;
    private String author;
    @CreatedDate
    private LocalDateTime registrationDate;

    public static Content dummyOf(String id, ContentType type){
        return Content.builder()
                .contentId(id)
                .contentType(type)
                .author("unknown")
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
