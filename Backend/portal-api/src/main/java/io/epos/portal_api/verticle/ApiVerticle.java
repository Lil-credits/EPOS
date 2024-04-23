package io.epos.portal_api.verticle;

import io.epos.portal_api.api.common.handler.ErrorHandler;
import io.epos.portal_api.api.handler.*;
import io.epos.portal_api.api.repository.BookRepository;
import io.epos.portal_api.api.repository.EducationModuleRepository;
import io.epos.portal_api.api.router.BookRouter;
import io.epos.portal_api.api.router.EducationModuleRouter;
import io.epos.portal_api.api.router.HealthCheckRouter;
import io.epos.portal_api.api.service.BookService;
import io.epos.portal_api.api.service.EducationModuleService;
import io.epos.portal_api.util.DbUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Pool;

public class ApiVerticle extends AbstractVerticle {


  @Override
  public void start(Promise<Void> promise) {
    final Pool dbClient = DbUtils.buildDbClient(vertx);

    final BookRepository bookRepository = new BookRepository();
    final BookService bookService = new BookService(dbClient, bookRepository);
    final BookHandler bookHandler = new BookHandler(bookService);
    final BookValidationHandler bookValidationHandler = new BookValidationHandler(vertx);
    final BookRouter bookRouter = new BookRouter(vertx, bookHandler, bookValidationHandler);

//    Education Modules setup
    final EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    final EducationModuleService educationModuleService = new EducationModuleService(dbClient, educationModuleRepository);
    final EducationModuleHandler educationModuleHandler = new EducationModuleHandler(educationModuleService);
    final EducationModuleValidationHandler educationModuleValidationHandler = new EducationModuleValidationHandler(vertx);
    final EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, educationModuleHandler, educationModuleValidationHandler);

    final Router router = Router.router(vertx);
    // test endpoint
    router.get("/").handler(ctx -> ctx.response().end("Hello World!"));
    ErrorHandler.buildHandler(router);
    HealthCheckRouter.setRouter(vertx, router, dbClient);
    bookRouter.setRouter(router);
    educationModuleRouter.setRouter(router);


    buildHttpServer(vertx, promise, router);
  }

  /**
   * Run HTTP server on port 8888 with specified routes
   *
   * @param vertx   Vertx context
   * @param promise Callback
   * @param router  Router
   */
  private void buildHttpServer(Vertx vertx,
                               Promise<Void> promise,
                               Router router) {
    final int port = 8080;

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(port, http -> {
        if (http.succeeded()) {
          promise.complete();
        } else {
          promise.fail(http.cause());
        }
      });
  }
}
