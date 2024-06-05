package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.epos.portal_api.api.common.exception.InvalidPathParameterException;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.epos.portal_api.api.common.ResponseBuilder.buildErrorResponse;
import static io.epos.portal_api.util.FileUtils.readJsonSchema;

/**
 * Handles validation for education module related API requests.
 */
public class EducationModuleValidationHandler extends BaseValidationHandler {

  private static final String SCHEMA_CREATE = "create_education_module.json";
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleValidationHandler.class);


  /**
   * Constructor for EducationModuleValidationHandler.
   *
   * @param vertx The Vert.x instance.
   */
  public EducationModuleValidationHandler(Vertx vertx) {
    super(vertx);
  }

  /**
   * Loads the JSON schemas for validation.
   *
   * @param vertx The Vert.x instance.
   */
  @Override
  protected void loadSchemas(Vertx vertx) {
    logger.info("Loading JSON schema for education module creation validation");
    schemaRepository.dereference(SCHEMA_CREATE, readJsonSchema(SCHEMA_CREATE, vertx));
  }

  /**
   * Validates the request for creating an education module.
   *
   * @param routingContext The routing context of the request.
   */
  public void create(RoutingContext routingContext) {
    logger.debug("Validating request for creating an education module");
    validateCreate(routingContext, SCHEMA_CREATE);
  }

    public void listIssuedCredentials(RoutingContext routingContext) {
      logger.debug("Validating request for listing issued credentials");
      if (isInvalidIntId(routingContext.request().getParam("id"))) {
        logger.error("Invalid path parameter: id must be a number" );
        buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid id, must be a number"));
      } else if (isInvalidIntId(routingContext.request().getParam("versionId"))) {
        logger.error("Invalid path parameter: versionId must be a number");
        buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid versionId, must be a number"));
      } else {
        routingContext.next();
      }
    }

    public boolean isInvalidIntId(String parameter){
        return parameter == null || !parameter.matches("\\d+");
    }
}
