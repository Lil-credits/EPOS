package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;

import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class AdminValidationHandler extends BaseValidationHandler {
  private static final String BASE_URI = "app://";
  private static final String SCHEMA_CREATE_COMPANY = "create_company.json";
  private static final String SCHEMA_CREATE_SUBSIDIARY = "create_subsidiary.json";
  private static final String SCHEMA_CREATE_ORGANISATION_UNIT = "create_organisation_unit.json";

  private static final String SCHEMA_CREATE_EDUCATION_MODULE = "create_education_module_admin.json";

  public AdminValidationHandler(Vertx vertx) {
    super(vertx, BASE_URI);
  }

  @Override
  protected void loadSchemas(Vertx vertx) {
    schemaRepository.dereference(SCHEMA_CREATE_COMPANY, readJsonSchema(SCHEMA_CREATE_COMPANY, vertx));
    schemaRepository.dereference(SCHEMA_CREATE_SUBSIDIARY, readJsonSchema(SCHEMA_CREATE_SUBSIDIARY, vertx));
    schemaRepository.dereference(SCHEMA_CREATE_ORGANISATION_UNIT, readJsonSchema(SCHEMA_CREATE_ORGANISATION_UNIT, vertx));
    schemaRepository.dereference(SCHEMA_CREATE_EDUCATION_MODULE, readJsonSchema(SCHEMA_CREATE_EDUCATION_MODULE, vertx));
  }

  public void createCompany(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_COMPANY);
  }

  public void createSubsidiary(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_SUBSIDIARY);
  }

  public void createOrganisationUnit(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_ORGANISATION_UNIT);
  }

  public void createEducationModule(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE_EDUCATION_MODULE);
  }
}
