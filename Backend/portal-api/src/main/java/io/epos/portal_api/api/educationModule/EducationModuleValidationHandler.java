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

  private static void initRepository(){
    // read education module schema from file


    // load it as json schema

    // defer to schema repository


  }


  /**
   * Build read one book request validation
   *
   * @return ValidationHandler
   */
  public void readOne(RoutingContext routingContext){
  }

  /**
   * Build create one book request validation
   *
   * @return ValidationHandler
   */
  public void create(RoutingContext routingContext){

    JsonObject json = new JsonObject()
      .put("courseName", "Nederlands")
      .put("studyYear", 2021)
      .put("description", "Dutch language course")
      .put("imageUrl", "google.nl")
      .put("requiredAchievements", new JsonArray().add("A2").add("B1"))
      .put("skills", new JsonArray().add("listening").add("speaking").add("reading").add("1"))
      .put("attributes", new JsonObject()
        .put("EC", "1")
        .put("language", "Dutch")
        .put("EQF", 1)
      );

    OutputUnit result = schemaRepository.validator("education_module.json").validate(json);
    JsonObject responseBody = new JsonObject();
    LOGGER.info("Validation result: " + result.getValid());
    if (!result.getValid()) {
      routingContext.fail(400, new Exception());
    }
    else {
      routingContext.next();
    }
  }
}
