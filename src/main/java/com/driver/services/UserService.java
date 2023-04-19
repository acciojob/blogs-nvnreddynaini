package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = User.builder().username(username).password(password).build();
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId){
        if(userRepository3.existsById(userId)){
            userRepository3.deleteById(userId);
        }
    }

    public User updateUser(Integer id, String password){

        User user = User.builder().id(id).build();
        user.setPassword(password);

        return user;
    }
}
