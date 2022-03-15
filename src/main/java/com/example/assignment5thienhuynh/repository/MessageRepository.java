package com.example.assignment5thienhuynh.repository;

import com.example.assignment5thienhuynh.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, Integer> {
}
