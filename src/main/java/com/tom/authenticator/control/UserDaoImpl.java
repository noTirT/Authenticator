package com.tom.authenticator.control;

import com.tom.authenticator.entity.User;
import com.tom.authenticator.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    //testing purposes
    public List<User> findAll(){
        return this.repository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOpt = this.repository.findById(id);
        if(userOpt.isPresent()) return userOpt.get();
        else throw new NotFoundException("No user with that id found");
    }

    @Override
    public void createUser(User user) {
        this.repository.save(user);
    }

    @Override
    public void updateUser(User user) {
        this.repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }
}
