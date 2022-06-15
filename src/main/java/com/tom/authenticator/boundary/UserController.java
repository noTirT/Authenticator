package com.tom.authenticator.boundary;

import com.tom.authenticator.control.UserServiceImpl;
import com.tom.authenticator.entity.RegisterRequest;
import com.tom.authenticator.entity.UpdateRequest;
import com.tom.authenticator.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    public static final String ROOT = "/auth";

    private UserServiceImpl userService;

    @GetMapping(ROOT + "/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping(ROOT + "/register")
    public ResponseEntity<Void> registerNewUser(@RequestBody RegisterRequest request) {
        userService.createUser(request.toUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(ROOT)
    public ResponseEntity<Void> updateUser(@RequestBody UpdateRequest request) {
        userService.updateUser(request.toUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(ROOT + "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
