package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.model.CreateEducationModuleRequest;
import io.epos.portal_api.api.educationModule.model.GetEducationModuleResponse;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.ResponseUtils;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static io.epos.portal_api.util.ResponseUtils.buildErrorResponse;
import static io.epos.portal_api.util.ResponseUtils.buildOkResponse;

public class EducationModuleHandler {

  private final EducationModuleService educationModuleService;
  private static final String ID_PARAMETER = "id";
  private static final String PAGE_PARAMETER = "page";
  private static final String LIMIT_PARAMETER = "limit";
  public EducationModuleHandler(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }
  public void readOne(RoutingContext routingContext) {

    final String id = routingContext.pathParam(ID_PARAMETER);
    educationModuleService.readOne(Integer.parseInt(id))
      .onSuccess(success -> ResponseUtils.buildOkResponse(routingContext, success))
      .onFailure(throwable -> ResponseUtils.buildErrorResponse(routingContext, throwable));
  }

  public void create(RoutingContext routingContext) {
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
    educationModuleService.create(createEducationModuleRequest)
      .onSuccess(success -> buildOkResponse(routingContext, success))
      .onFailure(throwable -> buildErrorResponse(routingContext, throwable));
  }

    public void readAll(RoutingContext rc) {
      educationModuleService.readAll(rc.queryParams().get(PAGE_PARAMETER), rc.queryParams().get(LIMIT_PARAMETER))
        .onSuccess(success -> ResponseUtils.buildOkResponse(rc, success))
        .onFailure(throwable -> ResponseUtils.buildErrorResponse(rc, throwable));
    }
}

