package io.epos.portal_api.api.educationModule;

import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EducationModuleRouter {
  private final Vertx vertx;
  private final EducationModuleValidationHandler validationHandler;
  private final EducationModuleController educationModuleController;
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleController.class);


  public EducationModuleRouter(Vertx vertx, EducationModuleController educationModuleController, EducationModuleValidationHandler validationHandler) {
    this.vertx = vertx;
    this.educationModuleController = educationModuleController;
    this.validationHandler = validationHandler;

  }

  /**
   * Set education module API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildEducationModuleRouter());}
  private Router buildEducationModuleRouter() {
    final Router educationModuleRouter = Router.router(vertx);
    educationModuleRouter.route("/education-module").respond(ctx -> ctx.response().end("Hello Education Module!"));
    educationModuleRouter.route("/education-modules*").handler(BodyHandler.create());
    educationModuleRouter.get("/education-modules/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::readOne).handler(educationModuleController::getEducationModule);
    educationModuleRouter.get("/education-modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::readAll).handler(educationModuleController::listEducationModules);
    educationModuleRouter.post("/education-modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::create).handler(educationModuleController::createEducationModule);
    return educationModuleRouter;
  }
}
