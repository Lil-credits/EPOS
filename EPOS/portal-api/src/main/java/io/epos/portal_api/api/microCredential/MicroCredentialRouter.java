package io.epos.portal_api.api.microCredential;

import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MicroCredentialRouter {
  private final Vertx vertx;
  private final MicroCredentialValidationHandler validationHandler;
  private final MicroCredentialController microCredentialController;
  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialRouter.class);

  public MicroCredentialRouter(Vertx vertx, MicroCredentialValidationHandler validationHandler, MicroCredentialController microCredentialController) {
    this.vertx = vertx;
    this.validationHandler = validationHandler;
    this.microCredentialController = microCredentialController;
  }

  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildMicroCredentialRouter());}
  private Router buildMicroCredentialRouter() {
    final Router microCredentialRouter = Router.router(vertx);
    microCredentialRouter.route("/micro-credential").respond(ctx -> ctx.response().end("Hello Micro Credential!"));
    microCredentialRouter.route("/micro-credentials*").handler(BodyHandler.create());
    microCredentialRouter.post("/micro-credentials/issue").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::issue).handler(microCredentialController::issue);
    microCredentialRouter.get("/micro-credentials").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::readAll).handler(microCredentialController::readAll);
    return microCredentialRouter;
  }
}
