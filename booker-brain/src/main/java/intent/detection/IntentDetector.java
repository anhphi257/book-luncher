package intent.detection;

import intent.model.Intent;
import model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public class IntentDetector {

    private static IntentDetector INSTANCE;

    private IntentDetector() {

    }

    public static IntentDetector getInstace() {
        if (INSTANCE == null) {
            INSTANCE = new IntentDetector();
        }
        return INSTANCE;
    }

    public Intent detect(Message message) {
        //TODO: implement your code here
        return null;
    }
}
