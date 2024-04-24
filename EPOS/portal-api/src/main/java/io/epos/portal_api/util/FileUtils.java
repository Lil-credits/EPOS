package io.epos.portal_api.util;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.JsonSchema;

public class FileUtils {
  private static final String JSON_SCHEMA_PATH = "src/main/resources/json_schema/";
  public static JsonSchema readJsonSchema(String filename, Vertx vertx) {
    FileSystem fileSystem = vertx.fileSystem();
    String filePath = JSON_SCHEMA_PATH + filename;
    Buffer buffer = fileSystem.readFileBlocking(filePath);
    String fileContent = buffer.toString();
    return JsonSchema.of(new JsonObject(fileContent));
  }
}
