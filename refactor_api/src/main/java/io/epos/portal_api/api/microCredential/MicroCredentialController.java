package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.ResponseBuilder;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MicroCredentialController {

  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialController.class);
  private final MicroCredentialService service;

  public MicroCredentialController(MicroCredentialService service) {
    this.service = service;
  }

  public void issue(RoutingContext routingContext) {
    logger.info("Issuing micro credential");
    service.issueMicroCredential(1, 1).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }

  public void readAll(RoutingContext routingContext) {
  }
}
