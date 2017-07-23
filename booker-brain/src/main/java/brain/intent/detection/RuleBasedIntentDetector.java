package brain.intent.detection;

import brain.intent.model.UserIntent;
import common.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public class RuleBasedIntentDetector implements IntentDetector {

    private static RuleBasedIntentDetector INSTANCE;

    private RuleBasedIntentDetector() {

    }

    public static RuleBasedIntentDetector getInstace() {
        if (INSTANCE == null) {
            INSTANCE = new RuleBasedIntentDetector();
        }
        return INSTANCE;
    }

    public UserIntent detect(Message message) {
        String content = message.getContent();
        content = content.toLowerCase();
        if(content.contains("hi") || content.contains("hello") || content.contains("chào")) {
            return UserIntent.GREETING;
        }
        if(content.contains("hủy") || content.contains("bỏ")) {
            return UserIntent.CANCEL;
        }
        if(content.contains("đặt") || content.contains("đăng ký")) {
            return UserIntent.REGISTER;
        }

        if(content.contains("kiểm tra") || content.contains("check")) {
            return UserIntent.CHECK;
        }
        if(content.contains("có") || content.contains("đúng")) {
            return UserIntent.AGREE;
        }
        if(content.contains("không")) {
            return UserIntent.DISAGREE;
        }

        return UserIntent.NONE;
    }
}
