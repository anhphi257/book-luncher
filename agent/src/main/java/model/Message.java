package model;

/**
 * Created by phiha on 21/07/2017.
 */
public class Message {
    private int id;
    private long time;
    private String content;

    public Message(int id, long time, String content) {
        this.id = id;
        this.time = time;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
