package agent.executor.imp;

import agent.executor.Agent;
import common.model.Conversation;
import common.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by phiha on 22/07/2017.
 */
public class BotAgent implements Agent {

    private static Logger LOG = LoggerFactory.getLogger(BotAgent.class);
    private Conversation conversation;

    public void receive(Message message) {
        conversation.addMessage(message);
    }

    @Override
    public Message getRespone() {
        return null;
    }

}
