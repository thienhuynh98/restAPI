package com.example.assignment5thienhuynh.controller;

import com.example.assignment5thienhuynh.model.User;
import com.example.assignment5thienhuynh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("user")
    public List<User> allUser()
    {
        return userRepository.findAll();
    }

    @PostMapping("user/add")
    public User addUser(@RequestBody User user)
    {
        return userRepository.save(user);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable int id)
    {
        if(userRepository.findByUserID(id) != null)
        {
            return userRepository.findByUserID(id);
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/user/{id}/interest")
    public User updateInterest(@RequestBody User user, @PathVariable int id)
    {
        if(userRepository.findByUserID(id) != null)
        {
            User tempUser = userRepository.findByUserID(id);
            tempUser.setInterest(user.getInterest());
            userRepository.save(tempUser);
            return userRepository.findByUserID(id);
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/user/{id}/active")
    public User updateActive(@RequestBody User user, @PathVariable int id)
    {
        if(userRepository.findByUserID(id) != null)
        {
            User tempUser = userRepository.findByUserID(id);
            tempUser.setActive(user.isActive());
            userRepository.save(tempUser);
            return userRepository.findByUserID(id);
        }
        else
        {
            return null;
        }
    }
}
