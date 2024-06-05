package io.epos.portal_api.api.educationModule;

import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Router class responsible for defining and configuring education module API routes.
 */
public class EducationModuleRouter {
  private final Vertx vertx;
  private final EducationModuleValidationHandler validationHandler;
  private final EducationModuleController educationModuleController;
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleRouter.class);

  /**
   * Constructs an EducationModuleRouter with the given dependencies.
   *
   * @param vertx                    Vert.x instance.
   * @param educationModuleController EducationModuleController instance.
   * @param validationHandler        EducationModuleValidationHandler instance.
   */
  public EducationModuleRouter(Vertx vertx, EducationModuleController educationModuleController, EducationModuleValidationHandler validationHandler) {
    this.vertx = vertx;
    this.educationModuleController = educationModuleController;
    this.validationHandler = validationHandler;
  }

  /**
   * Sets up education module API routes.
   *
   * @param router Router instance to configure routes.
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildEducationModuleRouter());
  }

  private Router buildEducationModuleRouter() {
    final Router educationModuleRouter = Router.router(vertx);

    // Basic route for education module endpoint
    educationModuleRouter.route("/education-module").respond(ctx -> ctx.response().end("Hello Education Module!"));

    // Configure body handler for request body parsing
    educationModuleRouter.route("/education-modules*").handler(BodyHandler.create());

    // Define routes for CRUD operations on education modules
    educationModuleRouter.get("/education-modules/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(validationHandler::readOne)
      .handler(educationModuleController::getEducationModule);

    educationModuleRouter.get("/education-modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(validationHandler::readAll)
      .handler(educationModuleController::listEducationModules);

    educationModuleRouter.post("/education-modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(validationHandler::create)
      .handler(educationModuleController::createEducationModule);

    educationModuleRouter.get("/education-modules/:id/versions/:versionId/issued-credentials").handler(LoggerHandler.create(LoggerFormat.DEFAULT))
      .handler(validationHandler::listIssuedCredentials)
      .handler(educationModuleController::getissuedCredentialsAccounts);

    // Log route configuration
    logger.info("Education module API routes configured successfully");

    return educationModuleRouter;
  }
}
