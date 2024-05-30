package io.epos.portal_api.api.router;

import io.epos.portal_api.api.controller.ImageController;
import io.epos.portal_api.api.educationModule.EducationModuleController;
import io.epos.portal_api.api.educationModule.EducationModuleValidationHandler;
import io.epos.portal_api.api.validation.ImageValidationHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageRouter {
  private final Vertx vertx;
  private final ImageValidationHandler validationHandler;
  private final ImageController controller;
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleController.class);


  public ImageRouter(Vertx vertx, ImageController imageController, ImageValidationHandler validationHandler) {
    this.vertx = vertx;
    this.controller = imageController;
    this.validationHandler = validationHandler;

  }

  /**
   * Set education module API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildImageRouter());}
  private Router buildImageRouter() {
    final Router imageRouter = Router.router(vertx);
    return imageRouter;
  }
}
