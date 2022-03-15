package com.example.assignment5thienhuynh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
@AllArgsConstructor
public class Event {
    @Id
    private int eventID;
    private int createdBy;
    private String eventName;
    private Date startedAt;
    private Date endedAt;
}
