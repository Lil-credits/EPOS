package io.epos.portal_api.api.microCredential;

import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sets up the routes for the MicroCredential API.
 */
public class MicroCredentialRouter {

  private final Vertx vertx;
  private final MicroCredentialValidationHandler validationHandler;
  private final MicroCredentialController microCredentialController;
  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialRouter.class);

  /**
   * Constructor for MicroCredentialRouter.
   *
   * @param vertx                      The Vert.x instance.
   * @param validationHandler          The handler for validating requests.
   * @param microCredentialController  The controller for handling micro-credential related requests.
   */
  public MicroCredentialRouter(Vertx vertx, MicroCredentialValidationHandler validationHandler, MicroCredentialController microCredentialController) {
    this.vertx = vertx;
    this.validationHandler = validationHandler;
    this.microCredentialController = microCredentialController;
  }

  /**
   * Sets the router for the micro-credential API.
   *
   * @param router The main router.
   */
  public void setRouter(Router router) {
    logger.info("Setting up routes for MicroCredential API");
    router.route("/api/v1/*").subRouter(buildMicroCredentialRouter());
  }

  /**
   * Builds the router for micro-credential API.
   *
   * @return The configured router.
   */
  private Router buildMicroCredentialRouter() {
    final Router microCredentialRouter = Router.router(vertx);

    logger.debug("Configuring routes for MicroCredential API");

    microCredentialRouter.route("/micro-credential").respond(ctx -> ctx.response().end("Hello Micro Credential!"));

    microCredentialRouter.route("/micro-credentials*").handler(BodyHandler.create());

    microCredentialRouter.post("/micro-credentials/issue")
      .handler(LoggerHandler.create())
      .handler(validationHandler::issue)
      .handler(microCredentialController::issue);

    microCredentialRouter.get("/micro-credentials")
      .handler(LoggerHandler.create())
      .handler(validationHandler::readAll)
      .handler(microCredentialController::readAll);

    return microCredentialRouter;
  }
}
