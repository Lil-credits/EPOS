package io.epos.portal_api.api;

import io.epos.portal_api.api.image.*;
import io.epos.portal_api.api.common.handler.ErrorHandler;
import io.epos.portal_api.api.common.router.HealthCheckRouter;
import io.epos.portal_api.api.educationModule.*;
import io.epos.portal_api.api.microCredential.*;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.sqlclient.Pool;

public class ApiInitializer {
  public static void initializeApis(Vertx vertx, Router router, Pool dbClient) {

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
    // Error handler for all APIs
    ErrorHandler.buildHandler(router);

    // Initialize all APIs here

    // Health check API
    HealthCheckRouter.buildRouter(vertx, router, dbClient);

    // Education Module API
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    EducationModuleService educationModuleService = new EducationModuleService(dbClient, educationModuleRepository);
    EducationModuleHandler educationModuleHandler = new EducationModuleHandler(educationModuleService);
    EducationModuleValidationHandler educationModuleValidationHandler = new EducationModuleValidationHandler(vertx);
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, educationModuleHandler, educationModuleValidationHandler);
    educationModuleRouter.setRouter(router);

    // Image API
    ImageRepository imageRepository = new ImageRepository();
    ImageService imageService = new ImageService(dbClient, imageRepository);
    ImageHandler imageHandler = new ImageHandler(imageService);
    ImageValidationHandler imageValidationHandler = new ImageValidationHandler(vertx);
    ImageRouter imageRouter = new ImageRouter(vertx, imageHandler, imageValidationHandler);
    imageRouter.setRouter(router);


    // MicroCredential API
    MicroCredentialRepository microCredentialRepository = new MicroCredentialRepository();
    MicroCredentialService microCredentialService = new MicroCredentialService(dbClient, microCredentialRepository);
    MicroCredentialHandler microCredentialHandler = new MicroCredentialHandler(microCredentialService);
    MicroCredentialValidationHandler microCredentialValidationHandler = new MicroCredentialValidationHandler(vertx);
    MicroCredentialRouter microCredentialRouter = new MicroCredentialRouter(vertx, microCredentialHandler, microCredentialValidationHandler);
    microCredentialRouter.setRouter(router);
  }
}
