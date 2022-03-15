package com.example.assignment5thienhuynh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
@AllArgsConstructor
public class Message {
    @Id
    private int messageID;
    private int userID;
    private String messageContent;
    private Date postedAt;
}
