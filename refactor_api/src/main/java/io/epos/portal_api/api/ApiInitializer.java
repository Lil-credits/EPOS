package io.epos.portal_api.api;



import io.epos.portal_api.api.product.ProductHandler;
import io.epos.portal_api.api.product.ProductRouter;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.CorsHandler;
import org.hibernate.reactive.mutiny.Mutiny;

public class ApiInitializer {
  public static void initializeApis(Vertx vertx, Router router, Mutiny.SessionFactory emf){

    // add CORS support
    // Create a CORS handler allowing all origins, methods, and headers
    CorsHandler corsHandler = CorsHandler.create()
      .addOrigin("*")
      .allowedMethod(HttpMethod.GET) // Allow all HTTP methods
      .allowedMethod(HttpMethod.POST)
      .allowedMethod(HttpMethod.PUT)
      .allowedMethod(HttpMethod.DELETE)
      .allowedMethod(HttpMethod.OPTIONS) // Also allow OPTIONS method
      .allowedHeader("*"); // Allow all headers

    router.route().handler(corsHandler);
    ProductRouter productRouter = new ProductRouter(vertx, new ProductHandler(emf));
    productRouter.setRouter(router);

  }
}
