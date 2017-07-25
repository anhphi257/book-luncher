package agent.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by hoangnh on 24/07/2017.
 */
public class ConnectFaceBook extends AbstractVerticle {
    private static String VERIFY_KEY = "check_my_url_web";
    private static String ACCESS_TOKEN = "EAAarK02w3YEBAKojM2WLvm76GdsHs4l5aGdeBZAz4N5IaTExVxgMYFe8rZBQcEB86NwtYJ18h3lyhmhzNvPpKHFkkOVTvuzivKekODWlJ6jtiwPrRttOaTcmq5LSLL0C2kPXWDNSlIHx5mZCZAc4PqZB985ZBhZABVsJoXd7AsIxaeZAB38RFuNZA";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String post(String url, String json) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.toString();
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Exception";
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.get("/").handler(this::getHome);
        router.get("/webhook").handler(this::getWebHook);
        router.post("/webhook").handler(this::postWebHook);

        vertx.createHttpServer();
        vertx.createHttpServer().requestHandler(router::accept).listen(9199);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setWorker(true);
        vertx.deployVerticle(new ConnectFaceBook(), deploymentOptions);
    }

    public void getHome(RoutingContext routingContext) {
        System.out.println("RUNNNIINNNGGGG");
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/text; charset=UTF-8");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.setStatusCode(200);
        response.end("Server run ok");
    }

    public void getWebHook(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        String mode = request.getParam("hub.mode");
        String key = request.getParam("hub.verify_token");
        String challenge = request.getParam("hub.challenge");
        if (mode.equals("subscribe") && key.equals(VERIFY_KEY)) {
            System.out.println("Verify success");
            response.putHeader("content-type", "application/json; charset=UTF-8");
            response.putHeader("Access-Control-Allow-Origin", "*");
            response.setStatusCode(200);
            response.end(challenge);

        } else {
            System.out.println("Error verify");
            response.putHeader("content-type", "application/json; charset=UTF-8");
            response.putHeader("Access-Control-Allow-Origin", "*");
            response.setStatusCode(403);
            response.end();
        }
    }

    public void postWebHook(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        System.out.println("Received a Message");

        routingContext.request().bodyHandler(buffer -> {
            String body = buffer.toString();
            System.out.println(body);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(body);

            String messageText = "toi khong hieu y ban";
            String object = element.getAsJsonObject().get("object").getAsString();
            String senderId = element.getAsJsonObject().get("entry").getAsJsonArray().get(0).getAsJsonObject().get("messaging").getAsJsonArray().get(0).getAsJsonObject().get("sender").getAsJsonObject().get("id").getAsString();
            try {
                messageText = element.getAsJsonObject().get("entry").getAsJsonArray().get(0).getAsJsonObject().get("messaging").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("text").getAsString();
            } catch (Exception e) {
                messageText = "Toi khong hieu y ban";
            }
            String recipientId = element.getAsJsonObject().get("entry").getAsJsonArray().get(0).getAsJsonObject().get("messaging").getAsJsonArray().get(0).getAsJsonObject().get("recipient").getAsJsonObject().get("id").getAsString();

            System.out.println("Object: " + object + ", senderId: " + senderId + ", text: " + messageText + ", recipientId: " + recipientId);

            sendMessage(senderId, messageText.toString());


        });
        response.putHeader("content-type", "application/json; charset=UTF-8");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.setStatusCode(200);
        response.end("OK");
    }

    public void receiveMessage(JsonObject object) {
        System.out.println(object.toString());
        String senderId = object.get("id").getAsString();
        JsonObject message = object.get("message").getAsJsonObject();
        String messageText = message.get("text").getAsString();
        sendMessage(senderId, messageText);
    }

    public void sendMessage(String recipientId, String messageText) {
        JsonObject recipient = new JsonObject();
        recipient.addProperty("id", recipientId);
        JsonObject message = new JsonObject();
        message.addProperty("text", messageText);
        JsonObject messageJson = new JsonObject();
        messageJson.add("recipient", recipient);
        messageJson.add("message", message);
        String messageData = messageJson.toString();
        System.out.println(messageData);
        callSendApi(messageData);
    }

    public void callSendApi(String messageData) {
        ConnectFaceBook connectFaceBook = new ConnectFaceBook();
        String url = " https://graph.facebook.com/v2.6/me/messages?access_token=" + ACCESS_TOKEN;
        String response = connectFaceBook.post(url, messageData);
        System.out.println(response);
    }

    public void testHandler(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        System.out.println("TestHandler");

        routingContext.request().bodyHandler(buffer -> {
            String body = buffer.toString();
            System.out.println("Test Handler: " + body);
            response.putHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            response.putHeader("Access-Control-Allow-Origin", "*");
            response.setStatusCode(200);
            response.end("OK");
        });
    }
}
