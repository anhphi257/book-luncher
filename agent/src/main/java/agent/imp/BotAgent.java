package agent.imp;

import agent.Agent;
import agent.model.Conversation;
import agent.model.Message;
import agent.model.Status;
import brain.executor.Executor;
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
        try {
            //TODO: implement method for sending message
            conversation.addMessage(responseMessage);
            conversation.setStatus(Status.CONTINUE);
            LOG.info("Sent successfully to user {}, content: {} ",
                    conversation.getUser().getId(), responseMessage.getContent());
        } catch (Exception e) {
            try {
                //TODO: implement method for sending message
                LOG.error("Failed! Retry again");
                conversation.addMessage(responseMessage);
                conversation.setStatus(Status.CONTINUE);
                LOG.info("Sent successfully to user {}, content: {} ",
                        conversation.getUser().getId(), responseMessage.getContent());
            } catch (Exception e1) {
                conversation.setStatus(Status.END);
                conversation.close();
            }
        }
    }


}
