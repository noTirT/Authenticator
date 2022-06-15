package com.tom.authenticator.control;

import com.tom.authenticator.entity.User;
import com.tom.authenticator.entity.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void createUser(User user) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        user.setPassword(encryptor.encryptPassword(user.getPassword()));
        userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<UserResponse> findAll() {
        return userDao.findAll().stream().map(UserResponse::of).collect(Collectors.toList());
    }
}
