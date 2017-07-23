package common.model;

/**
 * Created by phiha on 21/07/2017.
 */
public class User {
    private int id;
    private String name;

    public static User rootUser() {
        return new User(1, "VTCC");
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
