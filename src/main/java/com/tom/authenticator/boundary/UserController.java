package com.tom.authenticator.boundary;

import com.tom.authenticator.control.AuthServiceImpl;
import com.tom.authenticator.control.UserServiceImpl;
import com.tom.authenticator.entity.LoginRequest;
import com.tom.authenticator.entity.LoginResponse;
import com.tom.authenticator.entity.RegisterRequest;
import com.tom.authenticator.entity.UpdateRequest;
import com.tom.authenticator.entity.User;
import com.tom.authenticator.entity.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthServiceImpl authService;

    public static final String ROOT = "/auth";


    @GetMapping(ROOT + "/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(UserResponse.of(userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(ROOT + "/all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping(ROOT + "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request){
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping(ROOT + "/register")
    public ResponseEntity<Void> registerNewUser(@RequestBody RegisterRequest request) {
        userService.createUser(User.of(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(ROOT)
    public ResponseEntity<Void> updateUser(@RequestBody UpdateRequest request) {
        userService.updateUser(User.of(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(ROOT + "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
