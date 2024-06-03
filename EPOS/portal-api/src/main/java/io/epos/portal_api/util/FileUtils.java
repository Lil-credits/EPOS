package io.epos.portal_api.util;

import io.vertx.mutiny.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.json.schema.JsonSchema;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtils {
  public static JsonSchema readJsonSchema(String filename, Vertx vertx){
    try {
      InputStream inputStream = FileUtils.class.getResourceAsStream("/json_schema/" + filename);
      String fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      return JsonSchema.of(new JsonObject(fileContent));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  public static JsonObject readJsonObject(String filename){
    try {
      InputStream inputStream = FileUtils.class.getResourceAsStream("/json_schema/" + filename);
      String fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      return new JsonObject(fileContent);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
