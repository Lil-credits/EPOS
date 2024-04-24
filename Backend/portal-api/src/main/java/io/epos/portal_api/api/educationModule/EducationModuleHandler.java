package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.model.CreateEducationModuleRequest;
import io.epos.portal_api.domain.EducationModule;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static io.epos.portal_api.util.ResponseUtils.buildErrorResponse;
import static io.epos.portal_api.util.ResponseUtils.buildOkResponse;

public class EducationModuleHandler {

  private final EducationModuleService educationModuleService;
  public EducationModuleHandler(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }
  public void readOne(RoutingContext routingContext) {
  }

  public Future<EducationModule> create(RoutingContext routingContext) {
    JsonObject requestBody = routingContext.body().asJsonObject();


    // create a EducationModule object from the request body
    CreateEducationModuleRequest createEducationModuleRequest= new CreateEducationModuleRequest();
    createEducationModuleRequest.setCourseName(requestBody.getString("courseName"));
    createEducationModuleRequest.setStudyYear(requestBody.getInteger("studyYear"));
    createEducationModuleRequest.setDescription(requestBody.getString("description"));
    createEducationModuleRequest.setImageUrl(requestBody.getString("imageUrl"));
    createEducationModuleRequest.setRequiredAchievements(requestBody.getJsonArray("requiredAchievements"));
    createEducationModuleRequest.setSkills(requestBody.getJsonArray("skills"));
    createEducationModuleRequest.setAttributes(requestBody.getJsonObject("attributes"));
    // store the education module in the database
    return educationModuleService.create(createEducationModuleRequest)
      .onSuccess(success -> buildOkResponse(routingContext, success))
      .onFailure(throwable -> buildErrorResponse(routingContext, throwable));

    // create a EducationModuleVersion object from the request body
    // call the service to create the education module
    // call the service to create the education module version
    // return the created education module version as response or error
    // routingContext.response().end("Your shitty education module has been created!");
  }
}

