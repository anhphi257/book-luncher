package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phiha on 22/07/2017.
 */
public class Conversation {
    private int id;
    private List<Message> messages;
    private User user;
    private long startTime;
    private long endTime;
    private Status status;

    public Conversation() {
        messages = new ArrayList();
    }

    public Conversation(int id, List<Message> messages, User user, long startTime, long endTime) {

        this.id = id;
        this.messages = messages;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
