package com.example.assignment5thienhuynh.repository;

import com.example.assignment5thienhuynh.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, Integer> {
}
