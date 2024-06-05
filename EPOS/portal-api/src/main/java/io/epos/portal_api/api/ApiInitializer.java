package io.epos.portal_api.api;

import io.epos.portal_api.api.admin.AdminFactory;
import io.epos.portal_api.api.educationModule.EducationModuleFactory;
import io.epos.portal_api.api.microCredential.MicroCredentialFactory;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.CorsHandler;
import io.vertx.mutiny.ext.web.handler.SessionHandler;
import io.vertx.mutiny.ext.web.sstore.LocalSessionStore;
import io.vertx.mutiny.ext.web.sstore.SessionStore;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

/**
 * Initializes API components and sets up the router with necessary configurations.
 */
public class ApiInitializer {

  /**
   * Initializes all API components and configures the router.
   *
   * @param vertx  The Vert.x instance.
   * @param router The main router.
   * @param emf    The Hibernate session factory for database interactions.
   */
  public static void initializeApis(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    addSessionStorage(vertx, router);
    addCorsSupport(router);
    List<ApiComponentFactory> factories = getApiFactories();
    initializeFactories(vertx, router, emf, factories);
  }

  /**
   * Adds Session Storage to the router.
   *
   * @param vertx The Vert.x instance.
   * @param router The router to add session storage to.
   */

  private static void addSessionStorage(Vertx vertx, Router router) {
    SessionStore sessionStore = LocalSessionStore.create(vertx);
    SessionHandler sessionHandler = SessionHandler.create(sessionStore);
    router.route().handler(sessionHandler);
  }

  /**
   * Adds CORS support to the router.
   *
   * @param router The router to add CORS support to.
   */
  private static void addCorsSupport(Router router) {
    CorsHandler corsHandler = CorsHandler.create()
      .addOrigin("*")
      .allowedMethod(HttpMethod.GET)
      .allowedMethod(HttpMethod.POST)
      .allowedMethod(HttpMethod.PUT)
      .allowedMethod(HttpMethod.DELETE)
      .allowedMethod(HttpMethod.OPTIONS)
      .allowedHeader("*");

    router.route().handler(corsHandler);
  }

  /**
   * Returns a list of API component factories.
   *
   * @return A list of ApiComponentFactory instances.
   */
  private static List<ApiComponentFactory> getApiFactories() {
    return List.of(
      new EducationModuleFactory(),
      new MicroCredentialFactory(),
      new AdminFactory()
    );
  }

  /**
   * Initializes each factory and sets up the routes.
   *
   * @param vertx     The Vert.x instance.
   * @param router    The main router.
   * @param emf       The Hibernate session factory.
   * @param factories The list of factories to initialize.
   */
  private static void initializeFactories(Vertx vertx, Router router, Mutiny.SessionFactory emf, List<ApiComponentFactory> factories) {
    for (ApiComponentFactory factory : factories) {
      factory.create(vertx, router, emf);
    }
  }
}
