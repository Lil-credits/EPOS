package io.epos.portal_api.api;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public interface ApiComponentFactory {
  void create(Vertx vertx, Router router, Mutiny.SessionFactory emf);
}
