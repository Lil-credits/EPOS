package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.educationModule.EducationModuleService;
import io.epos.portal_api.util.ResponseUtils;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class MicroCredentialHandler {
  private final  MicroCredentialService microCredentialService;
  private static final Logger LOGGER = LoggerFactory.getLogger(MicroCredentialHandler.class);

  public MicroCredentialHandler(MicroCredentialService microCredentialService) {
    this.microCredentialService = microCredentialService;
  }

  public void issue(RoutingContext routingContext) {
    LOGGER.info("Issuing micro credential");
    JsonObject microCredential = routingContext.body().asJsonObject();

    microCredentialService.issue(microCredential.getInteger("educationModuleId"), microCredential.getInteger("userId"))
      .onSuccess(invitationLink -> {
        JsonObject invitationLinkJson = new JsonObject().put("invitationLink", invitationLink);
        ResponseUtils.buildOkResponse(routingContext, invitationLinkJson);})
      .onFailure(throwable -> {
        LOGGER.error("Failed to issue micro credential ", throwable);
        ResponseUtils.buildErrorResponse(routingContext, throwable);
        });

  }

  public void readAll(RoutingContext routingContext) {
  }

  public void issueBatch(RoutingContext routingContext) {
  }
}
