package io.epos.portal_api.api.image;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ImageHandler {
  public ImageHandler(ImageService imageService) {

  }

  public void create(RoutingContext routingContext) {
    // return imageUrl
    JsonObject response = new JsonObject().put("imageUrl", "data:image/jpeg;base64,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");
    routingContext.end(response.encodePrettily());
  }
}