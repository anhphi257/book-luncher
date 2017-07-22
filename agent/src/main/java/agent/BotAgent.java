package agent;

import model.Conversation;
import model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by phiha on 22/07/2017.
 */
public class BotAgent implements Agent{

    private static Logger LOG = LoggerFactory.getLogger(BotAgent.class);


    public void receive(Message message) {
        //TODO: implement your code here
        Conversation conversation = ConversationFactory.getConversation(message);

    }



    public void send(Conversation conversation, Message message) {
        //TODO: implement your code here
    }

}
