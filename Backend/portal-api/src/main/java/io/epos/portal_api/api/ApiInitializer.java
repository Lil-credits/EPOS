package io.epos.portal_api.api;

import io.epos.portal_api.api.common.handler.ErrorHandler;
import io.epos.portal_api.api.common.router.HealthCheckRouter;
import io.epos.portal_api.api.educationModule.*;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Pool;

public class ApiInitializer {
  public static void initializeApis(Vertx vertx, Router router, Pool dbClient) {
    // Initialize all APIs here

    // Error handler for all APIs
    ErrorHandler.buildHandler(router);

    // Health check API
    HealthCheckRouter.buildRouter(vertx, router, dbClient);

    // Education Module API
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    EducationModuleService educationModuleService = new EducationModuleService(dbClient, educationModuleRepository);
    EducationModuleHandler educationModuleHandler = new EducationModuleHandler(educationModuleService);
    EducationModuleValidationHandler educationModuleValidationHandler = new EducationModuleValidationHandler(vertx);
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, educationModuleHandler, educationModuleValidationHandler);
    educationModuleRouter.setRouter(router);

    // Additional apis can be initialized here
  }
}
