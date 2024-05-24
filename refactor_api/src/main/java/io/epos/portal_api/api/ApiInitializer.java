package io.epos.portal_api.api;



import io.epos.portal_api.api.educationModule.EducationModuleFactory;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.CorsHandler;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

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


    List<ApiComponentFactory> factories = List.of(
      new EducationModuleFactory()
    );

    // Initialize all APIs using factories
    for (ApiComponentFactory factory : factories) {
      factory.create(vertx, router, emf);
    }

  }
}
