package agent.io.utils;

import io.vertx.core.http.HttpServerResponse;

public class ResponseProvider {
    public static void sendSuccessRespone(HttpServerResponse response, String message) {
        response.putHeader("content-type", "application/json");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.setStatusCode(200);
        response.end(message);
    }

    public static void sendFailureResponse(HttpServerResponse response, String message) {
        response.putHeader("content-type", "application/text; charset=UTF-8");
        response.putHeader("Access-Control-Allow-Origin", "*");
        response.setStatusCode(403);
        response.end(message);
    }
}
