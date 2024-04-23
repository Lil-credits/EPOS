package io.epos.portal_api.api.educationModule;

import io.vertx.ext.web.RoutingContext;

public class EducationModuleHandler {

  private final EducationModuleService educationModuleService;
  public EducationModuleHandler(EducationModuleService educationModuleService) {
    this.educationModuleService = educationModuleService;
  }
  public void readOne(RoutingContext routingContext) {
  }

  public void create(RoutingContext routingContext) {
    routingContext.response().end("Your shitty education module has been created!");
  }
}
