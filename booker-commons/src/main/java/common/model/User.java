package common.model;

/**
 * Created by phiha on 21/07/2017.
 */
public class User {
    private long id;
    private String fbID;
    private String username;
    private String name;

    public static User rootUser() {
        return new User(1, "VTCC");
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
