package com.tom.authenticator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String username;
    private String email;
    private String role;
    private String jwtToken;
    private boolean successfulLogin;

    public static LoginResponse of(User user, boolean loggedIn, String jwtToken){
        return LoginResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .successfulLogin(loggedIn)
                .jwtToken(jwtToken)
                .build();
    }
}
