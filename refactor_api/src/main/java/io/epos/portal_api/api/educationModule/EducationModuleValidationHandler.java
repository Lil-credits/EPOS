package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;

import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class EducationModuleValidationHandler extends BaseValidationHandler {
  private static final String BASE_URI = "app://";

  public EducationModuleValidationHandler(Vertx vertx) {
    super(vertx, BASE_URI);
  }

  @Override
  protected void loadSchemas(Vertx vertx) {
    schemaRepository.dereference("create_education_module.json", readJsonSchema("create_education_module.json", vertx));
  }

  public void create(RoutingContext routingContext) {
    validateCreate(routingContext, "create_education_module.json");
  }
}
