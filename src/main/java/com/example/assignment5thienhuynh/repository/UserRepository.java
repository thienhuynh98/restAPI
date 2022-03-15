package com.example.assignment5thienhuynh.repository;

import com.example.assignment5thienhuynh.model.Group;
import com.example.assignment5thienhuynh.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    User findByUserID(int userID);
}
