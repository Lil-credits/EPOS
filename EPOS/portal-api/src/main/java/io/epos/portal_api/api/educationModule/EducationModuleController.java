package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.common.RequestParameters;
import io.epos.portal_api.api.common.ResponseBuilder;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.Image;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for handling education module related operations.
 */
public class EducationModuleController {
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleController.class);
  private final EducationModuleService educationModuleService;

  /**
   * Constructor to initialize the controller with the education module service.
   *
   * @param educationModuleService The education module service to be injected.
   */
  public EducationModuleController(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }

  /**
   * Retrieves an education module by its ID.
   *
   * @param routingContext The routing context containing request information.
   */
  public void getEducationModule(RoutingContext routingContext) {
    String id = routingContext.pathParam(RequestParameters.ID_PARAMETER);
    educationModuleService.getEducationModule(Integer.parseInt(id))
      .subscribe().with(
        educationModule -> ResponseBuilder.buildOkResponse(routingContext, educationModule),
        error -> {
          logger.error("Failed to get education module with ID: {}", id, error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }

  /**
   * Creates a new education module.
   *
   * @param routingContext The routing context containing request information.
   */
  public void createEducationModule(RoutingContext routingContext) {
    // Extract validated input data from routing context
    JsonObject body = routingContext.body().asJsonObject();
    String moduleName = body.getString("name");
    JsonObject baseCredential = body.getJsonObject("baseCredential");
    String imageData = body.getString("imageData");
    // Create EducationModuleVersion and Image objects
    EducationModuleVersion educationModuleVersion = createEducationModuleVersion(moduleName, baseCredential);
    Image image = createImage(imageData);
    // Create and save the education module
    educationModuleService.createEducationModule(new EducationModule(), educationModuleVersion, image)
      .subscribe().with(
        createdEducationModule -> ResponseBuilder.buildOkResponse(routingContext, createdEducationModule),
        error -> {
          logger.error("Failed to create education module", error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }

  private EducationModuleVersion createEducationModuleVersion(String moduleName, JsonObject baseCredential) {
    EducationModuleVersion educationModuleVersion = new EducationModuleVersion();
    educationModuleVersion.setName(moduleName);
    educationModuleVersion.setBaseCredential(baseCredential);
    return educationModuleVersion;
  }

  private Image createImage(String imageData) {
    Image image = new Image();
    image.setImageData(imageData);
    return image;
  }

  /**
   * Lists education modules based on pagination parameters.
   *
   * @param routingContext The routing context containing request information.
   */
  public void listEducationModules(RoutingContext routingContext) {
    educationModuleService.getEducationModules(routingContext.queryParams().get(RequestParameters.PAGE_PARAMETER),
        routingContext.queryParams().get(RequestParameters.LIMIT_PARAMETER))
      .subscribe().with(
        educationModules -> ResponseBuilder.buildOkResponse(routingContext, educationModules),
        error -> {
          logger.error("Failed to list education modules", error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }

  public void getissuedCredentialsAccounts(RoutingContext routingContext) {

    educationModuleService.getIssuedCredentialsAccounts(Integer.valueOf(routingContext.request().getParam("id")),Integer.valueOf(routingContext.request().getParam("versionId")))
      .subscribe().with(
        account -> ResponseBuilder.buildOkResponse(routingContext, account),
        error -> {
          logger.error("Failed to list issued credentials", error);
          ResponseBuilder.buildErrorResponse(routingContext, error);
        }
      );
  }
}
