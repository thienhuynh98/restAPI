package com.example.assignment5thienhuynh.controller;

import com.example.assignment5thienhuynh.model.Event;
import com.example.assignment5thienhuynh.model.Group;
import com.example.assignment5thienhuynh.model.Message;
import com.example.assignment5thienhuynh.model.Participant;
import com.example.assignment5thienhuynh.repository.EventRepository;
import com.example.assignment5thienhuynh.repository.GroupRepository;
import com.example.assignment5thienhuynh.repository.MessageRepository;
import com.example.assignment5thienhuynh.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/group")
    public List<Group> findAllGroup()
    {
        return groupRepository.findAll();
    }

    @GetMapping("group/{id}")
    public String getGroupName(@PathVariable int id)
    {
        Group temp = groupRepository.findByGroupID(id);
        if(temp == null)
        {
            return "This group does not exist";
        }
        else
        {
            return temp.getGroupName();
        }
    }

    @PostMapping("/group/create")
    public String createGroup(@RequestBody Group group)
    {
        Group temp = groupRepository.findByGroupID(group.getGroupID());
        if(temp == null)
        {
            groupRepository.save(group);
            return "Group is created";
        }
        else
        {
            return "There is something wrong with the request body";
        }
    }

    @PostMapping("/group/{id}/add")
    public String addParticipant(@RequestBody Participant participant, @PathVariable int id)
    {
        int i = 0;
        if(groupRepository.findByGroupID(id) != null)
        {
            Group editGroup = groupRepository.findByGroupID(id);
            for(Participant member : editGroup.getParticipants())
            {
                if(member.getParticipantID() != participant.getParticipantID())
                {
                    i++;
                }
            }
            if(editGroup.getParticipants().size() == i)
            {
                List<Participant> member = editGroup.getParticipants();
                member.add(participant);
                editGroup.setParticipants(member);
                groupRepository.save(editGroup);
                return "Added Succeed " + participant.getParticipantName();
            }
            else
            {
                return "This ID is already assigned to someone";
            }
        }
        else
        {
            return "Cannot find the group this participant would like to join";
        }
    }

    @DeleteMapping("/group/{id}/delete")
    public String deleteParticipant(@RequestBody Participant participant, @PathVariable int id)
    {
        int i = 0;
        if(groupRepository.findByGroupID(id) != null)
        {
            Group editGroup = groupRepository.findByGroupID(id);
            for(Participant member : editGroup.getParticipants())
            {
                if(member.getParticipantID() == participant.getParticipantID())
                {
                    editGroup.getParticipants().remove(i);
                    groupRepository.save(editGroup);
                    return "Successfully Delete " + participant.getParticipantName();
                }
                else
                {
                    i++;
                }
            }
            if(i == editGroup.getParticipants().size())
            {
                return "Cannot find this participant";
            }
        }
        else
        {
            return "Cannot find the group this participant would like to join";
        }
        return "Done";
    }

//    @GetMapping("group/{id}")
//    public Group findGroup(@PathVariable int id)
//    {
//        return groupRepository.findByGroupID(id);
//    }

    @PostMapping("group/{id}/message/add")
    public Group addMessage(@RequestBody Message message, @PathVariable int id)
    {
        Group editGroup = groupRepository.findByGroupID(id);
        List <Message>newMessage = editGroup.getMessages();
        newMessage.add(message);
        editGroup.setMessages(newMessage);
        return groupRepository.save(editGroup);
    }

    @PutMapping("group/{id}/message/update")
    public Group editMessage(@RequestBody Message message, @PathVariable int id)
    {
        Group editGroup = groupRepository.findByGroupID(id);
        for(Message editMessage : editGroup.getMessages())
        {
            if(editMessage.getMessageID() == message.getMessageID())
            {
                editMessage.setMessageContent(message.getMessageContent());
//                messageRepository.save(editMessage);
            }
        }
        return groupRepository.save(editGroup);
    }

    @GetMapping("group/{id}/message")
    public List<Message> getAllMessage(@PathVariable int id)
    {
        return groupRepository.findByGroupID(id).getMessages();
    }

    @PostMapping("group/event/{id}/add")
    public String addEvent(@RequestBody Event event, @PathVariable int id)
    {
        int i = 0;
        if(groupRepository.findByGroupID(id) != null)
        {
            Group eventGroup = groupRepository.findByGroupID(id);
            for(Event e : eventGroup.getEvents())
            {
                if(e.getEventID() != event.getEventID())
                {
                    i++;
                }
            }
            if(i == eventGroup.getEvents().size())
            {
                List<Event> temp = eventGroup.getEvents();
                temp.add(event);
                eventGroup.setEvents(temp);
                groupRepository.save(eventGroup);
                return "Successfully Add " + event.getEventName();
            }
            else
            {
                return event.getEventName() + " already exist";
            }
        }
        else
        {
            return "Cannot find the group to add this event";
        }
    }
    @GetMapping("group/{id}/event")
    public List<Event> allEvent(@PathVariable int id)
    {
        return groupRepository.findByGroupID(id).getEvents();
    }

    @GetMapping("group/{id}/event/{eventID}")
    public Event eventDetail(@PathVariable int id, @PathVariable int eventID)
    {
        Event temp = null;
        if(groupRepository.findByGroupID(id) != null && groupRepository.findByGroupID(id).getEvents().size() != 0)
        {
            for(Event event : groupRepository.findByGroupID(id).getEvents())
            {
                if(event.getEventID() == eventID)
                {
                    temp = event;
                }
            }
        }
        return temp;
    }

    @PutMapping("group/{id}/event/update/{eventID}")
    public List<Event> updateEvent(@PathVariable int id, @RequestBody Event event, @PathVariable int eventID)
    {
        if(groupRepository.findByGroupID(id) != null && groupRepository.findByGroupID(id).getEvents().size() != 0)
        {
            for(Event e : groupRepository.findByGroupID(id).getEvents())
            {
//                if(e.getCreatedBy() == event.getCreatedBy())
//                {
//                    e.setEventName(event.getEventName());
//                    e.setStartedAt(event.getStartedAt());
//                    e.setEndedAt(event.getEndedAt());
//                }
                System.out.println(e.getEventID());
                if(e.getEventID() == eventID)
                {
                    e.setEventName(event.getEventName());
                    e.setStartedAt(event.getStartedAt());
                    e.setEndedAt(event.getEndedAt());
                }
                groupRepository.save(groupRepository.findByGroupID(id));
            }
            return groupRepository.findByGroupID(id).getEvents();
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("group/{id}/event/delete/{eventID}")
    public String deleteEvent(@PathVariable int id, @PathVariable int eventID)
    {
        int i = -1;
        Group tempGroup = groupRepository.findByGroupID(id);
        if(tempGroup != null && groupRepository.findByGroupID(id).getEvents().size() != 0)
        {
            for(Event e : tempGroup.getEvents())
            {
                if(e.getEventID() == eventID)
                {
                    i = tempGroup.getEvents().indexOf(e);
                }
            }
        }
        if(i != -1)
        {
            tempGroup.getEvents().remove(i);
            groupRepository.save(tempGroup);
            return "Successfully Remove!";
        }
        else
        {
            return "There is something wrong with this request";
        }
    }
}
