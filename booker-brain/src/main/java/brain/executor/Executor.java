package brain.executor;


import common.model.Conversation;
import common.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public interface Executor {
    public Message execute(Conversation conversation);
}
