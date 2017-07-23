package brain.executor.imp;

import brain.executor.Executor;
import brain.intent.detection.IntentDetector;
import brain.intent.model.Intent;
import agent.model.Conversation;
import agent.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public class BotExecutor implements Executor {
    public Message execute(Conversation conversation) {
        Message message = conversation.getLastMessageFromUser();
        Intent intent = IntentDetector.getInstace().detect(message);
        switch (intent) {
            case GREETING:
                return doGreeting(conversation);
            case REGISTER:
                return  doRegister(conversation);
            case CANCEL:
                return doCancel(conversation);
            case CHECK:
                return doCheck(conversation);
            case OTHER:
                return doOther(conversation);
        }
        return null;
    }

    //TODO: implement your code here
    private Message doOther(Conversation conversation) {
        return null;
    }

    private Message doCheck(Conversation conversation) {
        return null;
    }

    private Message doCancel(Conversation conversation) {
        return null;
    }

    private Message doRegister(Conversation conversation) {
        return null;
    }

    private Message doGreeting(Conversation conversation) {
        return null;
    }
}
