package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.ext.web.RoutingContext;

import java.util.List;

public class EducationModuleHandler {

  private final EducationModuleService service;
  public EducationModuleHandler(EducationModuleService service) {
    this.service = service;
  }

  public Uni<EducationModule> getEducationModule(RoutingContext routingContext) {
    return null;
  }

  public Uni<EducationModule> createEducationModule(RoutingContext routingContext) {
    return null;
  }

  public Uni<List<EducationModule>> listEducationModules(RoutingContext routingContext) {
    return null;
  }
}
