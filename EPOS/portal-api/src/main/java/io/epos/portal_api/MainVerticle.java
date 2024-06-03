package io.epos.portal_api;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.epos.portal_api.api.ApiInitializer;
import io.epos.portal_api.util.db.DbUtil;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Persistence;

/**
 * Main Verticle responsible for initializing the application.
 */
public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  private Mutiny.SessionFactory emf;

  /**
   * Initiates the asynchronous start of the application.
   *
   * @return A Uni indicating the completion of the asynchronous start process
   */
  @Override
  public Uni<Void> asyncStart() {
    // Register the JSR-310 module with Jackson
    DatabindCodec.mapper().registerModule(new JavaTimeModule());

    // Start Hibernate asynchronously
    return startHibernate()
      .flatMap(ignored -> initializeRouter())
      .flatMap(this::startHttpServer)
      .onFailure().invoke(e -> logger.error("Failed to start application", e))
      .onItem().invoke(() -> logger.info("✅ Application started successfully"));
  }

  /**
   * Starts Hibernate asynchronously.
   *
   * @return A Uni indicating the completion of the Hibernate startup process
   */
  private Uni<Void> startHibernate() {
    Uni<Void> startHibernate = Uni.createFrom().deferred(() -> {
      emf = Persistence.createEntityManagerFactory("pg-epos", DbUtil.getHibernateProperties())
        .unwrap(Mutiny.SessionFactory.class);
      return Uni.createFrom().voidItem().invoke(() -> logger.info("✅ Hibernate Reactive is ready"));
    });
    return vertx.executeBlocking(startHibernate);
  }

  /**
   * Initializes the Router.
   *
   * @return A Uni emitting the initialized Router
   */
  private Uni<Router> initializeRouter() {
    Router router = Router.router(vertx);
    ApiInitializer.initializeApis(vertx, router, emf);
    return Uni.createFrom().item(router);
  }

  /**
   * Starts the HTTP server with the provided Router.
   *
   * @param router The initialized Router
   * @return A Uni indicating the completion of the HTTP server startup process
   */
  private Uni<Void> startHttpServer(Router router) {
    return vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .onItem().invoke(server -> logger.info("✅ HTTP server listening on port " + server.actualPort()))
      .replaceWithVoid();
  }
}
