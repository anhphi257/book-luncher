package intent;

/**
 * Created by phiha on 22/07/2017.
 */
public class Intent {

    private String type;

    public Intent() {
    }

    public Intent(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
