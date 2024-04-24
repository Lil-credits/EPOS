package io.epos.portal_api.api.image;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;

public class ImageRouter {
  private final Vertx vertx;
  private final ImageHandler imageHandler;
  private final ImageValidationHandler imageValidationHandler;

  public ImageRouter(Vertx vertx,
                     ImageHandler imageHandler,
                     ImageValidationHandler imageValidationHandler) {
    this.vertx = vertx;
    this.imageHandler = imageHandler;
    this.imageValidationHandler = imageValidationHandler;
  }

  /**
   * Set image API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildImageRouter());
  }

  /**
   * Build image API router
   * All routes are composed by an error handler, a validation handler and the actual business logic handler
   */
  private Router buildImageRouter() {
    final Router imageRouter = Router.router(vertx);

    // Test image router
    imageRouter.route("/image").handler(ctx -> ctx.response().end("Hello Image Router!"));
    imageRouter.route("/images*").handler(BodyHandler.create());
    imageRouter.post("/images").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(imageValidationHandler::create).handler(imageHandler::create);

    return imageRouter;
  }
}
