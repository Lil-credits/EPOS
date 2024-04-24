package io.epos.portal_api.verticle;

import io.epos.portal_api.api.ApiInitializer;
import io.epos.portal_api.util.DbUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Pool;

public class ApiVerticle extends AbstractVerticle {


  @Override
  public void start(Promise<Void> promise) {
    final Pool dbClient = DbUtils.buildDbClient(vertx);
    final Router router = Router.router(vertx);
    ApiInitializer.initializeApis(vertx, router, dbClient);
    buildHttpServer(vertx, promise, router);
  }

  /**
   * Run HTTP server on port 8080 with specified routes
   *
   * @param vertx   Vertx context
   * @param promise Callback
   * @param router  Router
   */
  private void buildHttpServer(Vertx vertx,
                               Promise<Void> promise,
                               Router router) {
    final int port = 8080;

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(port, http -> {
        if (http.succeeded()) {
          promise.complete();
        } else {
          promise.fail(http.cause());
        }
      });
  }
}
