package agent.io.handler;

import agent.executor.Agent;
import agent.executor.imp.BotAgent;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import common.model.Message;
import common.model.User;
import connector.UserDAO;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class WebHookHandler implements Handler<RoutingContext> {
    private static Logger LOG = LoggerFactory.getLogger(WebHookHandler.class);

    @Override
    public void handle(RoutingContext routingContext) {
        routingContext.request().bodyHandler(new Handler<Buffer>() {
            @Override
            public void handle(Buffer buffer) {
                String json = buffer.toString();
                String respone = process(json);
                Sender.send(respone);
            }
            private String process(String json) {

                JsonParser parser = new JsonParser();
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

                JsonObject recipient = new JsonObject();
                recipient.addProperty("id", fbId);

                JsonObject message = new JsonObject();
                message.addProperty("text", content);

                JsonObject messageJson = new JsonObject();
                messageJson.add("recipient", recipient);
                messageJson.add("message", message);
                String messageData = messageJson.toString();
                return messageData;
                // UserDAO userDAO = null;
                // try {
                //     //TODO: implement your code here, send message to agent, get message from agent.
                //     userDAO = new UserDAO();
                //     User user = userDAO.getUserByFbId(fbId);
                //     Message message = new Message(timestamp, user, content);
                //     Agent agent = new BotAgent();
                //     agent.receive(message);

                // } catch (SQLException e) {
                //     e.printStackTrace();
                // }
            }
        });
    }
    public static class Sender {
        public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static String send(String json) {
            String ACCESS_TOKEN = "";
            String url = " https://graph.facebook.com/v2.6/me/messages?access_token=" + ACCESS_TOKEN;
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "failure";
            }
        }
    }
}
