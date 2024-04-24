package io.epos.portal_api.util;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.JsonSchema;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtils {
  private static final String JSON_SCHEMA_PATH = "src/main/resources/json_schema/";
  public static JsonSchema readJsonSchema(String filename, Vertx vertx){
    try {
      InputStream inputStream = FileUtils.class.getResourceAsStream("/json_schema/" + filename);
      String fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      return JsonSchema.of(new JsonObject(fileContent));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
//    FileSystem fileSystem = vertx.fileSystem();
//    String filePath = JSON_SCHEMA_PATH + filename;
//    Buffer buffer = fileSystem.readFileBlocking(filePath);
//    String fileContent = buffer.toString();
//    return JsonSchema.of(new JsonObject(fileContent));
  }
}
