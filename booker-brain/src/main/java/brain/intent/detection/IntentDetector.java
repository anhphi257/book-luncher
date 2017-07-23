package brain.intent.detection;

import brain.intent.model.UserIntent;
import common.model.Message;

public interface IntentDetector {
    public UserIntent detect(Message message);
}
