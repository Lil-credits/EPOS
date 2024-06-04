package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.epos.portal_api.api.common.exception.InvalidPathParameterException;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.epos.portal_api.api.common.ResponseBuilder.buildErrorResponse;
import static io.epos.portal_api.util.FileUtils.readJsonSchema;

/**
 * Handles validation for micro-credential related API requests.
 */
public class MicroCredentialValidationHandler extends BaseValidationHandler {

  private static final String SCHEMA_CREATE = "create_micro_credential.json";
  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialValidationHandler.class);

  /**
   * Constructor for MicroCredentialValidationHandler.
   *
   * @param vertx The Vert.x instance.
   */
  public MicroCredentialValidationHandler(Vertx vertx) {
    super(vertx);
  }

  /**
   * Loads the JSON schemas for validation.
   *
   * @param vertx The Vert.x instance.
   */
  @Override
  protected void loadSchemas(Vertx vertx) {
    logger.info("Loading JSON schema for micro-credential creation validation");
    schemaRepository.dereference(SCHEMA_CREATE, readJsonSchema(SCHEMA_CREATE, vertx));
  }

  /**
   * Validates the request for issuing a micro-credential.
   *
   * @param routingContext The routing context of the request.
   */
  public void issue(RoutingContext routingContext) {
    logger.debug("Validating request for issuing a micro-credential");
    validateCreate(routingContext, SCHEMA_CREATE);
  }

  public void readCredentials(RoutingContext routingContext) {
    logger.debug("Validating request for reading micro-credentials");
    String membershipId = routingContext.request().getParam("membershipId");
    String organisationalUnitId = routingContext.request().getParam("organisationalUnitId");
    boolean hasError = false;
    if (membershipId != null && !membershipId.matches("\\d+")) {
      logger.error("Invalid query parameter: membership id must be a number");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid membership id, must be a number"));
      hasError = true;
    }

    if (organisationalUnitId != null && !organisationalUnitId.matches("\\d+")) {
      logger.error("Invalid query parameter: organisational unit id must be a number");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Invalid organisational unit id, must be a number"));
      hasError = true;
    }

    if(organisationalUnitId == null && membershipId == null){
      logger.error("Invalid query parameter: either membership id or organisational unit id must be provided");
      buildErrorResponse(routingContext, new InvalidPathParameterException("Either membership id or organisational unit id must be provided"));
      hasError = true;
    }

    if (!hasError) {
      routingContext.next();
    }
  }
}
