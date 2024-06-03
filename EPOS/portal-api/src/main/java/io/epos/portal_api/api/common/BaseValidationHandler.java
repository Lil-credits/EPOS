package io.epos.portal_api.api.common;

import io.epos.portal_api.api.common.exception.InvalidPathParameterException;
import io.epos.portal_api.api.common.exception.InvalidRequestBodyException;
import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.JsonSchemaOptions;
import io.vertx.json.schema.OutputUnit;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;
import io.vertx.mutiny.json.schema.SchemaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.epos.portal_api.api.common.ResponseBuilder.buildErrorResponse;

/**
 * Abstract base class for validation handlers.
 * Provides common validation logic for handling requests.
 */
public abstract class BaseValidationHandler {

  private static final String BASE_URI = "app://";
  protected final SchemaRepository schemaRepository;
  private static final Logger logger = LoggerFactory.getLogger(BaseValidationHandler.class);

  /**
   * Constructor for BaseValidationHandler.
   *
   * @param vertx The Vert.x instance.
   */
  public BaseValidationHandler(Vertx vertx) {
    this.schemaRepository = SchemaRepository.create(new JsonSchemaOptions().setBaseUri(BASE_URI));
    loadSchemas(vertx);
  }

  /**
   * Abstract method to load schemas.
   * Subclasses should implement this method to load their specific schemas.
   *
   * @param vertx The Vert.x instance.
   */
  protected abstract void loadSchemas(Vertx vertx);

  /**
   * Validates the ID parameter in the request.
   *
   * @param routingContext The routing context of the request.
   */
  public void readOne(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    if (id == null || !id.matches("\\d+")) {
      logger.error("Invalid path parameter: id must be a number");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid id, must be a number"));
    } else {
      routingContext.next();
    }
  }

  /**
   * Validates the limit and page parameters in the request.
   *
   * @param routingContext The routing context of the request.
   */
  public void readAll(RoutingContext routingContext) {
    String limit = routingContext.request().getParam("limit");
    String page = routingContext.request().getParam("page");
    boolean hasError = false;

    if (limit != null && !limit.matches("\\d+")) {
      logger.error("Invalid query parameter: limit must be a number");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid limit, must be a number"));
      hasError = true;
    }

    if (page != null && !page.matches("\\d+")) {
      logger.error("Invalid query parameter: page must be a number");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid page, must be a number"));
      hasError = true;
    }

    if (!hasError) {
      routingContext.next();
    }
  }

  /**
   * Validates the request body against a JSON schema.
   *
   * @param routingContext The routing context of the request.
   * @param schemaPath     The path to the JSON schema.
   */
  public void validateCreate(RoutingContext routingContext, String schemaPath) {
    JsonObject requestBody = routingContext.body().asJsonObject();
    OutputUnit result = schemaRepository.validator(schemaPath).validate(requestBody);

    if (!result.getValid()) {
      logger.error("Invalid request body for schema {}: {}", schemaPath, result.getErrors());
      buildErrorResponse(routingContext, new InvalidRequestBodyException("Invalid request body"));
    } else {
      routingContext.next();
    }
  }
}
