package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.model.CreateEducationModuleRequest;
import io.epos.portal_api.api.educationModule.model.GetEducationModuleResponse;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import static io.epos.portal_api.util.ResponseUtils.buildErrorResponse;
import static io.epos.portal_api.util.ResponseUtils.buildOkResponse;

public class EducationModuleHandler {

  private final EducationModuleService educationModuleService;
  public EducationModuleHandler(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }
  public Future<GetEducationModuleResponse> readOne(RoutingContext routingContext) {
    return null;
  }

  public Future<EducationModuleVersion> create(RoutingContext routingContext) {
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
  }

    public Future<GetEducationModuleResponse> readAll(RoutingContext routingContext) {
      return null;
    }
}

