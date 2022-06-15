package com.tom.authenticator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birthdate", nullable = false)
    private long birthDateTimestamp;
    @Column(name = "role", nullable = false)
    private String role;

    public static User of(RegisterRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .birthDateTimestamp(request.getBirthDateTimestamp())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public static User of(UpdateRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .birthDateTimestamp(request.getBirthDateTimestamp())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }
}
