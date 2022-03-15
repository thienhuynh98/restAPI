package com.example.assignment5thienhuynh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Document (collection = "user")
public class User {
    @Id
    private int userID;
    private String userName;
    private Date joinedAt;
    private boolean active;

    private List<String> interest;
}
