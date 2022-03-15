package com.example.assignment5thienhuynh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "database")
public class Group {
    @Id
    private int groupID;
    private String groupName;
    private List<Participant> participants;
    private List<Message> messages;
    private List<Event> events;
}
