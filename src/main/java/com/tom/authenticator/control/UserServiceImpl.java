package com.tom.authenticator.control;

import com.tom.authenticator.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private UserDaoImpl userDao;

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }
}
