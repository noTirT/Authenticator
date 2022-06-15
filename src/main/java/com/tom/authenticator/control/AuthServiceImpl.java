package com.tom.authenticator.control;

import com.tom.authenticator.entity.LoginRequest;
import com.tom.authenticator.entity.LoginResponse;
import com.tom.authenticator.entity.User;
import com.tom.authenticator.exception.BadCredentialsException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository repository;

    public LoginResponse login(LoginRequest request) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        Optional<User> userOpt = this.repository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());
        if (userOpt.isPresent()) {
            if (encryptor.checkPassword(request.getPassword(), userOpt.get().getPassword())) {
                return LoginResponse.of(userOpt.get(), true);
            } else throw new BadCredentialsException("Wrong password");
        } else throw new BadCredentialsException("User with that username or email does not exist");
    }
}
