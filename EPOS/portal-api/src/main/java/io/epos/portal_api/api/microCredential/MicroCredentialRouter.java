package io.epos.portal_api.api.microCredential;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;

public class MicroCredentialRouter {
  private final Vertx vertx;
  private final MicroCredentialHandler microCredentialHandler;
  private final MicroCredentialValidationHandler microCredentialValidationHandler;
  public MicroCredentialRouter(Vertx vertx, MicroCredentialHandler microCredentialHandler, MicroCredentialValidationHandler microCredentialValidationHandler) {
    this.vertx = vertx;
    this.microCredentialHandler = microCredentialHandler;
    this.microCredentialValidationHandler = microCredentialValidationHandler;
  }
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildMicroCredentialRouter());}

  /**
   * Build education module API
   * All routes are composed by an error handler, a validation handler and the actual business logic handler
   */
  private Router buildMicroCredentialRouter() {
    final Router microCredentialRouter = Router.router(vertx);

    //test book router
    microCredentialRouter.route("/micro-credential").handler(ctx -> ctx.response().end("Hello Micro Credential!"));
    microCredentialRouter.route("/micro-credentials*").handler(BodyHandler.create());
    microCredentialRouter.post("/micro-credentials/issue").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(microCredentialValidationHandler::issue).handler(microCredentialHandler::issue);
    microCredentialRouter.get("/micro-credentials").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(microCredentialValidationHandler::readAll).handler(microCredentialHandler::readAll);
    return microCredentialRouter;
  }
}
