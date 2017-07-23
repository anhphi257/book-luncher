package brain.executor;


import agent.model.Conversation;
import agent.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public interface Executor {
    public Message execute(Conversation conversation);
}
