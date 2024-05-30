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


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  private Mutiny.SessionFactory emf;  // <1>

  @Override
  public Uni<Void> asyncStart() {
    // Register the JSR-310 module with Jackson
    DatabindCodec.mapper().registerModule(new JavaTimeModule());
    return startHibernate()
      .flatMap(this::startHttpServer)
      .onFailure().invoke(e -> logger.error("Failed to start application", e))
      .onItem().invoke(() -> logger.info("✅ Application started successfully"));
  }

  private Uni<Router> startHibernate() {
    // Define the blocking operation as a Uni
    Uni<Void> startHibernate = Uni.createFrom().deferred(() -> {
      emf = Persistence.createEntityManagerFactory("pg-epos", DbUtil.getHibernateProperties())
        .unwrap(Mutiny.SessionFactory.class);
      return Uni.createFrom().voidItem();
    });

    // Execute the blocking operation on a worker thread
    return vertx.executeBlocking(startHibernate)
      .onItem()
      .transform(ignored -> {
        Router router = Router.router(vertx);
        logger.info("✅ Hibernate Reactive is ready");
        ApiInitializer.initializeApis(vertx, router, emf);
        return router;
      });
  }

  private Uni<Void> startHttpServer(Router router) {
    return vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .onItem().invoke(server -> logger.info("✅ HTTP server listening on port " + server.actualPort()))
      .replaceWithVoid();
  }
}
