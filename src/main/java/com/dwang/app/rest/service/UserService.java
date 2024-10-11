package com.dwang.app.rest.service;

import com.dwang.app.rest.exception.InvalidUserRequestException;
import com.dwang.app.rest.exception.UserNotFoundException;
import com.dwang.app.rest.models.User;
import com.dwang.app.rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(long id, User updatedUser){
        if(userRepo.findById(id).isEmpty()){
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        User currentUser = userRepo.findById(id).get();
        if(updatedUser.getAge() == 40){
            throw new InvalidUserRequestException("Cannot input of this age: " + updatedUser.getAge());
        }
        //how can we avoid updating null on fields that are not given to us from body
        //ans: just use manual checks if null then set field
        currentUser.setAge(updatedUser.getAge());
        currentUser.setOccupation(updatedUser.getOccupation());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        return userRepo.save(currentUser);
    }

    public User partialUpdateUser(long id, User updatedUser){
        if(userRepo.findById(id).isEmpty()){
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        User currentUser = userRepo.findById(id).get();
        if(updatedUser.getAge() == 40){
            throw new InvalidUserRequestException("Cannot input of this age: " + updatedUser.getAge());
        }
        currentUser.setAge(updatedUser.getAge());
        currentUser.setOccupation(updatedUser.getOccupation());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setDescription(updatedUser.getDescription());
        return userRepo.save(currentUser);
    }

    public String deleteUser(long id){
        if(userRepo.findById(id).isEmpty()){
            throw new UserNotFoundException("User not found with this ID: " + id);
        }
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleted user: " + id;
    }

    public User getUserByFirstName(String firstName){
        return userRepo.findByFirstName(firstName);
    }
}
