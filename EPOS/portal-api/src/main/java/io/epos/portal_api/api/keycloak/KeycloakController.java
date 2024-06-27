package io.epos.portal_api.api.keycloak;

import io.epos.portal_api.api.common.ResponseBuilder;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for handling Keycloak-related operations.
 */
public class KeycloakController {
  private static final Logger logger = LoggerFactory.getLogger(KeycloakController.class);
  private final KeycloakService keycloakService;

  /**
   * Constructor to initialize the controller with the Keycloak service.
   *
   * @param keycloakService The Keycloak service to be injected.
   */
  public KeycloakController(KeycloakService keycloakService) {
    this.keycloakService = keycloakService;
  }

  /**
   * Handles user login.
   *
   * @param routingContext The routing context containing request information.
   */
  public void login(RoutingContext routingContext) {
    JsonObject body = routingContext.body().asJsonObject();
    String username = body.getString("username");
    String password = body.getString("password");

    keycloakService.authenticate(username, password)
      .subscribe().with(
        user -> {
          keycloakService.setTokens(routingContext, user);
          ResponseBuilder.buildOkResponse(routingContext, "User logged in");
        },
        error -> {
          logger.error("Failed to authenticate user", error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }

  /**
   * Secures endpoints by validating the access token or refreshing it if necessary.
   *
   * @param routingContext The routing context containing request information.
   */
  public void secureEndpoints(RoutingContext routingContext) {
    keycloakService.secureEndpoints(routingContext)
      .subscribe().with(
        next -> routingContext.next(),
        error -> {
          logger.error("Failed to secure endpoint", error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }

  /**
   * Handles access to a secure endpoint.
   *
   * @param routingContext The routing context containing request information.
   */
  public void secure1(RoutingContext routingContext) {
    ResponseBuilder.buildOkResponse(routingContext, "User is authorized");
  }

  /**
   * Handles access to a secure endpoint for users with the "student" role.
   *
   * @param routingContext The routing context containing request information.
   */
  public void secure2(RoutingContext routingContext) {
    if (keycloakService.isUserInRole(routingContext, "student")) {
      ResponseBuilder.buildOkResponse(routingContext, "User is student");
    } else {
      ResponseBuilder.buildErrorResponse(routingContext, new Throwable("401: Not authorized"));
    }
  }

  /**
   * Handles access to a secure endpoint for users with the "teacher" role.
   *
   * @param routingContext The routing context containing request information.
   */
  public void secure3(RoutingContext routingContext) {
    if (keycloakService.isUserInRole(routingContext, "teacher")) {
      ResponseBuilder.buildOkResponse(routingContext, "User is teacher");
    } else {
      ResponseBuilder.buildErrorResponse(routingContext, new Throwable("401: Not authorized"));
    }
  }

  /**
   * Retrieves user details.
   *
   * @param routingContext The routing context containing request information.
   */
  public void userDetails(RoutingContext routingContext) {
    JsonObject response = keycloakService.getUserDetails(routingContext);
    ResponseBuilder.buildOkResponse(routingContext, response);
  }
}

