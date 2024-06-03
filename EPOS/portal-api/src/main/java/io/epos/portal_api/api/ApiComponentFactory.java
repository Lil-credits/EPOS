package io.epos.portal_api.api;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

/**
 * Interface for creating API components.
 * Implementations should provide the necessary setup for the specific API module.
 */
public interface ApiComponentFactory {

  /**
   * Creates and sets up the necessary components for the API module.
   *
   * @param vertx  The Vert.x instance.
   * @param router The router to set up routes.
   * @param emf    The Hibernate session factory for database interactions.
   */
  void create(Vertx vertx, Router router, Mutiny.SessionFactory emf);
}
