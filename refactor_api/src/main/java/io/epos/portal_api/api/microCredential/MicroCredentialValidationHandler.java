package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.BaseValidationHandler;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.RoutingContext;

import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class MicroCredentialValidationHandler extends BaseValidationHandler {
  private static final String BASE_URI = "app://";
  private static final String SCHEMA_CREATE = "create_micro_credential.json";

  public MicroCredentialValidationHandler(Vertx vertx) {
    super(vertx, BASE_URI);
  }

  @Override
  protected void loadSchemas(Vertx vertx) {
    schemaRepository.dereference(SCHEMA_CREATE, readJsonSchema(SCHEMA_CREATE, vertx));
  }
  public void issue(RoutingContext routingContext) {
    validateCreate(routingContext, SCHEMA_CREATE);
  }
}
