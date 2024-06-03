package io.epos.portal_api.util.db;

import io.vertx.core.json.JsonArray;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter for JSON arrays.
 */
@Converter(autoApply = true)
public class JsonArrayConverter implements AttributeConverter<JsonArray, String> {

  @Override
  public String convertToDatabaseColumn(JsonArray attribute) {
    return attribute != null ? attribute.encode() : null;
  }

  @Override
  public JsonArray convertToEntityAttribute(String dbData) {
    return dbData != null ? new JsonArray(dbData) : null;
  }
}
