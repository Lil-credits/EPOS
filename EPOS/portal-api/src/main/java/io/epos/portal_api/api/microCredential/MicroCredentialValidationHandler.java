package io.epos.portal_api.api.microCredential;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.json.schema.JsonSchemaOptions;
import io.vertx.json.schema.OutputUnit;
import io.vertx.json.schema.SchemaRepository;

import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class MicroCredentialValidationHandler {
  private final SchemaRepository schemaRepository = SchemaRepository.create(new JsonSchemaOptions().setBaseUri("app://"));

  public MicroCredentialValidationHandler(Vertx vertx) {
    schemaRepository.dereference("create_micro_credential.json", readJsonSchema("create_micro_credential.json", vertx));
  }
  public void issue(RoutingContext routingContext) {
    JsonObject requestBody = routingContext.body().asJsonObject();
    OutputUnit result = schemaRepository.validator("create_micro_credential.json").validate(requestBody);
    if (!result.getValid()) {
      routingContext.fail(400, new Exception());
    }
    else {
      routingContext.next();
    }
  }

  public void readAll(RoutingContext routingContext) {
  }

  public void issueBatch(RoutingContext routingContext) {
  }
}
