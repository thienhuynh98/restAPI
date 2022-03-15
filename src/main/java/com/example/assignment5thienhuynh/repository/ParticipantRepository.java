package com.example.assignment5thienhuynh.repository;

import com.example.assignment5thienhuynh.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipantRepository extends MongoRepository<Participant, Integer> {
}
