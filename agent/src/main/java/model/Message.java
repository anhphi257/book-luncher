package model;

/**
 * Created by phiha on 21/07/2017.
 */
public class Message {
    private int id;
    private long time;
    private User user;

    public Message(int id, long time, User user) {
        this.id = id;
        this.time = time;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
