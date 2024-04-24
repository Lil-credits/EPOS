package io.epos.portal_api.api.Image;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class ImageValidationHandler {
  public ImageValidationHandler(Vertx vertx) {

  }

  public void create(RoutingContext routingContext) {
    routingContext.next();
  }
}
