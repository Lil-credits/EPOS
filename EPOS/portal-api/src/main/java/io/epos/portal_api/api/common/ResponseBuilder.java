package io.epos.portal_api.api.common;

import io.epos.portal_api.api.common.exception.BadRequestException;
import io.epos.portal_api.api.common.exception.NotFoundException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.RoutingContext;

import java.util.NoSuchElementException;

/**
 * Utility class for building HTTP responses.
 */
public final class ResponseBuilder {

  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";

  // Private constructor to prevent instantiation
  private ResponseBuilder() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * Builds a 200 OK success response with the given response body.
   *
   * @param rc       the routing context
   * @param response the response body
   */
  public static void buildOkResponse(RoutingContext rc, Object response) {
    rc.response()
      .setStatusCode(200)
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .endAndForget(Json.encodePrettily(response));
  }

  /**
   * Builds a 201 Created success response with the given response body.
   *
   * @param rc       the routing context
   * @param response the response body
   */
  public static void buildCreatedResponse(RoutingContext rc, Object response) {
    rc.response()
      .setStatusCode(201)
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .endAndForget(Json.encodePrettily(response));
  }

  /**
   * Builds a 204 No Content success response with no response body.
   *
   * @param rc the routing context
   */
  public static void buildNoContentResponse(RoutingContext rc) {
    rc.response()
      .setStatusCode(204)
      .endAndForget();
  }
  /**
   * Builds an error response based on the type of the given throwable.
   * - 400 Bad Request for IllegalArgumentException, IllegalStateException, or NullPointerException.
   * - 404 Not Found for NoSuchElementException.
   * - 500 Internal Server Error for other exceptions.
   *
   * @param rc        the routing context
   * @param throwable the throwable that caused the error
   */
  public static void buildErrorResponse(RoutingContext rc, Throwable throwable) {
    final int status;
    final String message;

    if (throwable instanceof BadRequestException) {
      // Bad Request
      status = ((BadRequestException) throwable).getStatusCode();
      message = throwable.getMessage();
    } else if (throwable instanceof NotFoundException) {
      // Not Found
      status = ((NotFoundException) throwable).getStatusCode();
      message = throwable.getMessage();
    } else {
      // Internal Server Error
      status = 500;
      message = throwable.getMessage();
    }

    rc.response()
      .setStatusCode(status)
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .endAndForget(new JsonObject().put("error", message).encodePrettily());
  }
}



