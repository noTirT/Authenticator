package com.tom.authenticator.control;

import com.tom.authenticator.entity.RegisterRequest;
import com.tom.authenticator.entity.User;

public interface UserService {
    User findById(String id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(String id);
}
