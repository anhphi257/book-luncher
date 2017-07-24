package agent.io;

import agent.io.handler.AppHandler;
import agent.io.handler.WebHookHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class WebHookAPI extends AbstractVerticle{
    public void start() {
        Router router = Router.router(vertx);

        router.get("/webhook").handler(new AppHandler());
        router.post("/webhook").handler(new WebHookHandler());
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions();
        options.setWorker(true);
        vertx.deployVerticle(new WebHookAPI(), options);
    }
}
