package agent.io.handler;

import agent.io.utils.ResponseProvider;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppHandler implements Handler<RoutingContext> {

    private static Logger LOG = LoggerFactory.getLogger(AppHandler.class);
    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        String myToken = "123456a@";
        String verifyToken = request.getParam("hub.verify_token");
        if (verifyToken.equals(myToken)) {
            String challenge = request.getParam("hub.challenge");
            ResponseProvider.sendSuccessRespone(response, challenge);
        } else {
            ResponseProvider.sendFailureResponse(response, "Wrong validation token");
            LOG.info("Wrong validation token");
        }
    }
}
