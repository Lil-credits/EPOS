package io.epos.portal_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.templates.annotations.Column;

public class EducationModuleVersion {

  private int id;
  private int version;
  private String description;
  private JsonObject attributes;
  private JsonArray requiredAchievements;
  private JsonArray skills;
  private int educationModuleID;
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
