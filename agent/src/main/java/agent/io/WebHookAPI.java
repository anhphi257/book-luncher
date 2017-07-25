package agent.io;

import agent.io.handler.DefaultHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class WebHookAPI extends AbstractVerticle{
    public void start() {
        Router router = Router.router(vertx);

//        router.get("/webhook").handler(new AppHandler());
//        router.post("/webhook").handler(new WebHookHandler());
        router.get("/api").handler(new DefaultHandler());
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions();
        options.setWorker(true);
        vertx.deployVerticle(new WebHookAPI(), options);
    }
}
