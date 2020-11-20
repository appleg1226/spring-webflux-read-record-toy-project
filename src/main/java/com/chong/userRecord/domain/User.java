package com.chong.userRecord.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull @Email
    private String email;

    public static User dummyOf(String id){
        return User.builder()
                .id(id)
                .email("dummy@email.com")
                .name("unknown")
                .build();
    }
}
