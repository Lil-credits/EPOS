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

public abstract class BaseValidationHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(BaseValidationHandler.class);
  protected final SchemaRepository schemaRepository;

  public BaseValidationHandler(Vertx vertx, String baseUri) {
    this.schemaRepository = SchemaRepository.create(new JsonSchemaOptions().setBaseUri(baseUri));
    loadSchemas(vertx);
  }

  protected abstract void loadSchemas(Vertx vertx);

  public void readOne(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    if (id == null || !id.matches("\\d+")) {
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid id, must be a number"));
    } else {
      routingContext.next();
    }
  }

  public void readAll(RoutingContext routingContext) {
    String limit = routingContext.request().getParam("limit");
    String page = routingContext.request().getParam("page");
    if (limit != null && !limit.matches("\\d+")) {
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid limit, must be a number"));
    }
    if (page != null && !page.matches("\\d+")) {
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid page, must be a number"));
    }
    routingContext.next();
  }

  public void validateCreate(RoutingContext routingContext, String schemaPath) {
    JsonObject requestBody = routingContext.body().asJsonObject();
    OutputUnit result = schemaRepository.validator(schemaPath).validate(requestBody);
    if (!result.getValid()) {
      buildErrorResponse(routingContext, new InvalidRequestBodyException("Invalid education module request body"));
    } else {
      routingContext.next();
    }
  }
}
