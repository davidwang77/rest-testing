package com.dwang.app.rest.controller;

import com.dwang.app.rest.models.User;
import com.dwang.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/partial-update/{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userService.partialUpdateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //does post mapping not override? if null fields are in body

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/get/{firstName}")
    public User getUserByFirstName(@PathVariable String firstName){
        //if multiple items are returned how to handle, it throws 500 for multiple users right now
        return userService.getUserByFirstName(firstName);
    }
}
