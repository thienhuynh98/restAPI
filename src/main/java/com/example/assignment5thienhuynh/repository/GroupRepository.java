package com.example.assignment5thienhuynh.repository;

import com.example.assignment5thienhuynh.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, Integer>
{
    Group findByGroupID(int groupID);
}
