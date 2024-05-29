package io.epos.portal_api.api.educationModule.dto;

import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EducationModuleVersionResponseDTO {
  private int id;
  private String name;
  private JsonObject baseCredential;
  private LocalDate effectuationDate;

  private LocalDateTime createdAt;

  private int educationModuleId;

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

  public LocalDate getEffectuationDate() {
    return effectuationDate;
  }

  public void setEffectuationDate(LocalDate effectuationDate) {
    this.effectuationDate = effectuationDate;
  }

  public int getEducationModuleId() {
    return educationModuleId;
  }

  public void setEducationModuleId(int educationModuleId) {
    this.educationModuleId = educationModuleId;
  }
}
