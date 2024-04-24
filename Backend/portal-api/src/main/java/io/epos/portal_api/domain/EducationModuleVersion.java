package io.epos.portal_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class EducationModuleVersion {
  @JsonProperty("id")
  private int id;

  @JsonProperty("version")
  private int version;

  @JsonProperty("description")
  private String description;

  @JsonProperty("attributes")
  private JsonObject attributes;

  @JsonProperty("requiredAchievements")
  private JsonArray requiredAchievements;

  @JsonProperty("skills")
  private JsonArray skills;

  @JsonProperty("educationModuleID")
  private int educationModuleID;

  @JsonProperty("status")
  private String status;

  public EducationModuleVersion() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public JsonObject getAttributes() {
    return attributes;
  }

  public void setAttributes(JsonObject attributes) {
    this.attributes = attributes;
  }

  public JsonArray getRequiredAchievements() {
    return requiredAchievements;
  }

  public void setRequiredAchievements(JsonArray requiredAchievements) {
    this.requiredAchievements = requiredAchievements;
  }

  public JsonArray getSkills() {
    return skills;
  }

  public void setSkills(JsonArray skills) {
    this.skills = skills;
  }

  public int getEducationModuleID() {
    return educationModuleID;
  }

  public void setEducationModuleID(int educationModuleID) {
    this.educationModuleID = educationModuleID;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
