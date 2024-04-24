package io.epos.portal_api.api.educationModule;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.json.schema.*;
import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class EducationModuleValidationHandler {
  // logger
  private static final Logger LOGGER = LoggerFactory.getLogger(EducationModuleValidationHandler.class);
  private final SchemaRepository schemaRepository = SchemaRepository.create(new JsonSchemaOptions().setBaseUri("app://"));
  public EducationModuleValidationHandler(Vertx vertx) {
    schemaRepository.dereference("education_module.json", readJsonSchema("education_module.json", vertx));
  }
  public void readOne(RoutingContext routingContext){
  }
  public void create(RoutingContext routingContext){
    JsonObject requestBody = routingContext.body().asJsonObject();
    OutputUnit result = schemaRepository.validator("education_module.json").validate(requestBody);
    LOGGER.info("Validation result: " + result.getValid());
    if (!result.getValid()) {
      routingContext.fail(400, new Exception());
    }
    else {
      routingContext.next();
    }
  }
}
