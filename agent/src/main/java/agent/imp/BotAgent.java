package agent.imp;

import agent.Agent;
import agent.io.AgentIO;
import brain.executor.Executor;
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
    private Executor executor;

    public BotAgent(Conversation conversation, Executor executor) {
        this.conversation = conversation;
        this.executor = executor;
    }

    public void receive(Message message) {
        conversation.addMessage(message);
    }

    public void send() {
        Message responseMessage = executor.execute(conversation);
        System.out.println("Respone: " + responseMessage.getContent());
        AgentIO.sendMessage(conversation, responseMessage);

    }


}
