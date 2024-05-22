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
  private final EducationModuleHandler educationModuleHandler;
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleHandler.class);


  public EducationModuleRouter(Vertx vertx, EducationModuleHandler educationModuleHandler, EducationModuleValidationHandler validationHandler) {
    this.vertx = vertx;
    this.educationModuleHandler = educationModuleHandler;
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
    educationModuleRouter.route("/education_modules*").handler(BodyHandler.create());
    educationModuleRouter.get("/education_modules/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).respond(educationModuleHandler::getEducationModule);
    educationModuleRouter.post("/education_modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).respond(educationModuleHandler::createEducationModule);
    educationModuleRouter.get("/education_modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).respond(educationModuleHandler::listEducationModules);
    return educationModuleRouter;
  }
}
