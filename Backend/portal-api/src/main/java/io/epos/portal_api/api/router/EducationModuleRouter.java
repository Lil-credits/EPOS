package io.epos.portal_api.api.router;

import io.epos.portal_api.api.handler.BookHandler;
import io.epos.portal_api.api.handler.BookValidationHandler;
import io.epos.portal_api.api.handler.EducationModuleHandler;
import io.epos.portal_api.api.handler.EducationModuleValidationHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;

public class EducationModuleRouter {
  private final Vertx vertx;
  private final EducationModuleHandler educationModuleHandler;
  private final EducationModuleValidationHandler educationModuleValidationHandler;

  public EducationModuleRouter(Vertx vertx,
                    EducationModuleHandler educationModuleHandler,
                    EducationModuleValidationHandler educationModuleValidationHandler) {
    this.vertx = vertx;
    this.educationModuleHandler = educationModuleHandler;
    this.educationModuleValidationHandler = educationModuleValidationHandler;
  }

  /**
   * Set education module API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildBookRouter());}

  /**
   * Build education module API
   * All routes are composed by an error handler, a validation handler and the actual business logic handler
   */
  private Router buildBookRouter() {
    final Router educationModudleRouter = Router.router(vertx);

    //test book router
    educationModudleRouter.route("/education-module").handler(ctx -> ctx.response().end("Hello Education Module!"));
    educationModudleRouter.route("/education-modules*").handler(BodyHandler.create());
    educationModudleRouter.get("/education-modules/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(educationModuleValidationHandler::readOne).handler(educationModuleHandler::readOne);
    educationModudleRouter.post("/education-modules").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(educationModuleValidationHandler::create).handler(educationModuleHandler::create);

    return educationModudleRouter;
  }
}
