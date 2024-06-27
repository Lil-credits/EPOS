package io.epos.portal_api.api.keycloak;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.epos.portal_api.api.ApiComponentFactory;
import org.hibernate.reactive.mutiny.Mutiny;

/**
 * Factory class responsible for creating Keycloak components.
 */
public class KeycloakFactory implements ApiComponentFactory {

  /**
   * Creates and configures Keycloak components.
   *
   * @param vertx  Vert.x instance.
   * @param router Router instance to configure routes.
   */
  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    // Create service
    KeycloakService keycloakService = new KeycloakService(vertx);

    // Create controller and inject service
    KeycloakController keycloakController = new KeycloakController(keycloakService);

    // Create router and set up routes
    KeycloakRouter keycloakRouter = new KeycloakRouter(vertx, keycloakController);
    keycloakRouter.setRouter(router);
  }
}
