package io.epos.portal_api.api.product;


import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;

public class ProductRouter {
  private final Vertx vertx;
  private final ProductHandler productHandler;

  public ProductRouter(Vertx vertx, ProductHandler productHandler) {
    this.vertx = vertx;
    this.productHandler = productHandler;
  }

  /**
   * Set education module API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/example/*").subRouter(buildProductRouter());}
  private Router buildProductRouter() {
    final Router productRouter = Router.router(vertx);

    productRouter.route("/products*").handler(BodyHandler.create());
    productRouter.get("/products/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(productHandler::getProduct);
    productRouter.post("/products").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).respond(productHandler::createProduct);
    productRouter.get("/products").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).respond(productHandler::listProducts);
    return productRouter;
  }

}
