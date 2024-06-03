package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.ResponseBuilder;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for handling micro credential related operations.
 */
public class MicroCredentialController {

  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialController.class);
  private final MicroCredentialService service;

  /**
   * Constructor to initialize the controller with the micro credential service.
   *
   * @param service The micro credential service to be injected.
   */
  public MicroCredentialController(MicroCredentialService service) {
    this.service = service;
  }

  /**
   * Issues a micro credential based on the provided request data.
   *
   * @param routingContext The routing context containing request information.
   */
  public void issue(RoutingContext routingContext) {
    logger.info("Issuing micro credential");

    // Extract required parameters from the request body
    JsonObject body = routingContext.body().asJsonObject();
    Integer issuerId = body.getInteger("issuerId");
    Integer subjectId = body.getInteger("subjectId");
    Integer educationModuleVersionId = body.getInteger("educationModuleVersionId");

    // Log the extracted parameters
    logger.debug("issuerId: {}, subjectId: {}, educationModuleVersionId: {}", issuerId, subjectId, educationModuleVersionId);

    // Call the service to issue the micro credential and handle the response
    service.issueMicroCredential(issuerId, subjectId, educationModuleVersionId).subscribe().with(
      result -> {
        logger.info("Successfully issued micro credential");
        ResponseBuilder.buildOkResponse(routingContext, new JsonObject().put("invitationLink", result));
      },
      error -> {
        logger.error("Failed to issue micro credential", error);
        ResponseBuilder.buildErrorResponse(routingContext, error);
      }
    );
  }

  /**
   * Placeholder method for reading all micro credentials.
   *
   * @param routingContext The routing context containing request information.
   */
  public void readAll(RoutingContext routingContext) {
    // Placeholder implementation for reading all micro credentials
    logger.info("Reading all micro credentials");
    // TODO: Implement the readAll method
  }
}
