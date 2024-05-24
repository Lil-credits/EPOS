package io.epos.portal_api.api.educationModule.dto;

import io.vertx.core.json.JsonObject;

import java.util.Date;

public class EducationModuleVersionResponseDTO {
  private int id;
  private String name;
  private JsonObject baseCredential;
  private Date effectuationDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public JsonObject getBaseCredential() {
    return baseCredential;
  }

  public void setBaseCredential(JsonObject baseCredential) {
    this.baseCredential = baseCredential;
  }

  public Date getEffectuationDate() {
    return effectuationDate;
  }

  public void setEffectuationDate(Date effectuationDate) {
    this.effectuationDate = effectuationDate;
  }
}