package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    loadSchemas(vertx);
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
}
