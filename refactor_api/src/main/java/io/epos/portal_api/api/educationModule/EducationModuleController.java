package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.common.RequestParameters;
import io.epos.portal_api.api.common.ResponseBuilder;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EducationModuleController {
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleController.class);
  private final EducationModuleService educationModuleService;
  public EducationModuleController(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }

  public void getEducationModule(RoutingContext routingContext) {
    String id = routingContext.pathParam(RequestParameters.ID_PARAMETER);

    educationModuleService.getEducationModule(Integer.parseInt(id))
      .subscribe().with(
        educationModule -> ResponseBuilder.buildOkResponse(routingContext, educationModule),
        error -> ResponseBuilder.buildErrorResponse(routingContext, error)
      );
  }

  public void createEducationModule(RoutingContext routingContext) {
    return;
  }

  public void listEducationModules(RoutingContext routingContext) {
    return;
  }
}
