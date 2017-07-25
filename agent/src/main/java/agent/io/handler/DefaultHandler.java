package agent.io.handler;

import agent.io.utils.ResponseProvider;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


public class DefaultHandler implements Handler<RoutingContext>{

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        ResponseProvider.sendSuccessRespone(response, "Running");
    }
}
