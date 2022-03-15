package com.example.assignment5thienhuynh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Participant {
    @Id
    private int participantID;
    private String participantName;
}
