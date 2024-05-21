package io.epos.portal_api.api;

import io.epos.portal_api.api.image.*;
import io.epos.portal_api.api.common.handler.ErrorHandler;
import io.epos.portal_api.api.common.router.HealthCheckRouter;
import io.epos.portal_api.api.educationModule.*;
import io.epos.portal_api.api.microCredential.*;
import io.epos.portal_api.api.user.UserRepository;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.epos.portal_api.util.DbUtils;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.sqlclient.Pool;

public class ApiInitializer {

  public static void initializeApis(Vertx vertx, Router router) {
    final Pool dbClient = DbUtils.buildDbClient(vertx);
    final WaltidClient waltidClient = new WaltidClient(vertx);
    configureCors(router);
    ErrorHandler.buildHandler(router);
    initializeHealthCheckApi(vertx, router, dbClient);
    initializeEducationModuleApi(vertx, router, dbClient);
    initializeImageApi(vertx, router, dbClient);
    initializeMicroCredentialApi(vertx, router, dbClient, waltidClient);
  }

  private static void configureCors(Router router) {
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

  private static void initializeHealthCheckApi(Vertx vertx, Router router, Pool dbClient) {
    HealthCheckRouter.buildRouter(vertx, router, dbClient);
  }

  private static void initializeEducationModuleApi(Vertx vertx, Router router, Pool dbClient) {
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    EducationModuleService educationModuleService = new EducationModuleService(dbClient, educationModuleRepository);
    EducationModuleHandler educationModuleHandler = new EducationModuleHandler(educationModuleService);
    EducationModuleValidationHandler educationModuleValidationHandler = new EducationModuleValidationHandler(vertx);
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, educationModuleHandler, educationModuleValidationHandler);
    educationModuleRouter.setRouter(router);
  }

  private static void initializeImageApi(Vertx vertx, Router router, Pool dbClient) {
    ImageRepository imageRepository = new ImageRepository();
    ImageService imageService = new ImageService(dbClient, imageRepository);
    ImageHandler imageHandler = new ImageHandler(imageService);
    ImageValidationHandler imageValidationHandler = new ImageValidationHandler(vertx);
    ImageRouter imageRouter = new ImageRouter(vertx, imageHandler, imageValidationHandler);
    imageRouter.setRouter(router);
  }

  private static void initializeMicroCredentialApi(Vertx vertx, Router router, Pool dbClient, WaltidClient waltidClient) {
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    MicroCredentialRepository microCredentialRepository = new MicroCredentialRepository();
    UserRepository userRepository = new UserRepository();
    MicroCredentialService microCredentialService = new MicroCredentialService(dbClient, microCredentialRepository, waltidClient, educationModuleRepository, userRepository);
    MicroCredentialHandler microCredentialHandler = new MicroCredentialHandler(microCredentialService);
    MicroCredentialValidationHandler microCredentialValidationHandler = new MicroCredentialValidationHandler(vertx);
    MicroCredentialRouter microCredentialRouter = new MicroCredentialRouter(vertx, microCredentialHandler, microCredentialValidationHandler);
    microCredentialRouter.setRouter(router);
  }
}
