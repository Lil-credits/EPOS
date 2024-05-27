package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;

import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class AdminValidationHandler extends BaseValidationHandler {
  private static final String BASE_URI = "app://";
  private static final String SCHEMA_CREATE_COMPANY = "create_company.json";
  private static final String SCHEMA_CREATE_SUBSIDIARY = "create_subsidiary.json";

  public AdminValidationHandler(Vertx vertx) {
    super(vertx, BASE_URI);
  }

  @Override
  protected void loadSchemas(Vertx vertx) {
    schemaRepository.dereference(SCHEMA_CREATE_COMPANY, readJsonSchema(SCHEMA_CREATE_COMPANY, vertx));
    schemaRepository.dereference(SCHEMA_CREATE_SUBSIDIARY, readJsonSchema(SCHEMA_CREATE_SUBSIDIARY, vertx));
  }

  public void createCompany(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_COMPANY);
  }

  public void createSubsidiary(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_SUBSIDIARY);
  }
}
