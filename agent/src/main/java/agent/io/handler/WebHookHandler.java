package agent.io.handler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import common.model.Message;
import common.model.User;
import connector.UserDAO;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class WebHookHandler implements Handler<RoutingContext> {
    private static Logger LOG = LoggerFactory.getLogger(WebHookHandler.class);

    @Override
    public void handle(RoutingContext routingContext) {
        JsonParser parser = new JsonParser();
        routingContext.request().bodyHandler(new Handler<Buffer>() {
            @Override
            public void handle(Buffer buffer) {
                String json = buffer.toString();
                JsonElement element = parser.parse(json);

                JsonElement messagingElement = element.getAsJsonObject().get("entry")
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("messaging");

                long fbId = messagingElement
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("sender")
                        .getAsJsonObject().get("id")
                        .getAsLong();
                String content = messagingElement
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("message")
                        .getAsJsonObject().get("text")
                        .getAsString();
                long timestamp = messagingElement
                        .getAsJsonArray().get(0)
                        .getAsJsonObject().get("timestamp")
                        .getAsLong();

                UserDAO userDAO = null;
                try {
                    //TODO: implement your code here, send message to agent, get message from agent.
                    userDAO = new UserDAO();
                    User user = userDAO.getUserByFbId(fbId);
                    Message message = new Message(timestamp, user, content);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
