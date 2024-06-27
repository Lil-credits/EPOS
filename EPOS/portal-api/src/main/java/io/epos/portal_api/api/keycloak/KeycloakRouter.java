package io.epos.portal_api.api.keycloak;

import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Router class responsible for defining and configuring Keycloak API routes.
 */
public class KeycloakRouter {
  private final Vertx vertx;
  private final KeycloakController keycloakController;
  private static final Logger logger = LoggerFactory.getLogger(KeycloakRouter.class);

  public KeycloakRouter(Vertx vertx, KeycloakController keycloakController) {
    this.vertx = vertx;
    this.keycloakController = keycloakController;
  }

  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildKeycloakRouter());
  }

  private Router buildKeycloakRouter() {
    final Router keycloakRouter = Router.router(vertx);

    keycloakRouter.route().handler(BodyHandler.create());

    keycloakRouter.post("/auth/login").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(keycloakController::login);

    keycloakRouter.route().handler(keycloakController::secureEndpoints);

    keycloakRouter.get("/secure/1").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(keycloakController::secure1);

    keycloakRouter.get("/secure/2").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(keycloakController::secure2);

    keycloakRouter.get("/secure/3").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(keycloakController::secure3);

    keycloakRouter.get("/secure/user-details").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(keycloakController::userDetails);

    logger.info("Keycloak API routes configured successfully");

    return keycloakRouter;
  }
}
