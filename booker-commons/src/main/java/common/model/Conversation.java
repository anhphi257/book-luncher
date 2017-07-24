package common.model;

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
    private ConversationStatus status;

    public Conversation() {
        status = ConversationStatus.START;
        messages = new ArrayList();
    }

    public Conversation(int id, List<Message> messages, User user, long startTime, long endTime) {

        this.id = id;
        this.messages = messages;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ConversationStatus getStatus() {
        return status;
    }

    public void setStatus(ConversationStatus status) {
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

    public Message getLastMessageFromUser() {
        //TODO: implement your code here
        for(int i = messages.size() - 1; i >= 0; i--) {
            if(messages.get(i).getUser().getId() != 1) {
                return messages.get(i);
            }
        }
        return null;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void close() {
        setEndTime(System.currentTimeMillis());
        //TODO: update conversation to database
    }
}
