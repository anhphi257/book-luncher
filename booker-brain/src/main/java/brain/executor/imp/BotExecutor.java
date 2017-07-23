package brain.executor.imp;

import brain.executor.Executor;
import brain.executor.MessageProvider;
import brain.intent.detection.RuleBasedIntentDetector;
import brain.intent.model.UserIntent;
import common.model.Conversation;
import common.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public class BotExecutor implements Executor {
    public Message execute(Conversation conversation) {
        Message message = conversation.getLastMessageFromUser();
        UserIntent intent = RuleBasedIntentDetector.getInstace().detect(message);
        return MessageProvider.getMessage(intent, conversation);
    }

}
