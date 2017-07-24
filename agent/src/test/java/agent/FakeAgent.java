package agent;


import agent.imp.BotAgent;
import brain.executor.Executor;
import brain.executor.imp.BotExecutor;
import common.model.Conversation;
import common.model.Message;
import common.model.User;
import org.junit.Test;

public class FakeAgent {
    @Test
    public void test01fakeConversation() {

        //create fake message
        User fakeUser = new User(2, "fakeUser");
        Message fakeMessage = new Message(1, System.currentTimeMillis(), fakeUser, "Cho em đặt cơm hôm nay với ạ");

        //create fake agent
        Conversation fakeConversation = new Conversation();
        fakeConversation.setUser(fakeUser);
        Executor fakeExecutor = new BotExecutor();
        Agent agent = new BotAgent(fakeConversation, fakeExecutor);

        //fake operations
        agent.receive(fakeMessage);
        agent.send();
    }
}
