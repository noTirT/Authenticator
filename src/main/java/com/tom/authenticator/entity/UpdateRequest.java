package com.tom.authenticator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private long birthDateTimestamp;

    public User toUser(){
        return User.builder()
                .password(this.password)
                .birthDateTimestamp(this.birthDateTimestamp)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .username(this.username)
                .build();
    }
}
