package io.epos.portal_api.api.educationModule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class CreateEducationModuleRequest {

  @JsonProperty("courseName")
  private String courseName;

  @JsonProperty("studyYear")
  private int studyYear;

  @JsonProperty("description")
  private String description;

  @JsonProperty("imageUrl")
  private String imageUrl;

  @JsonProperty("requiredAchievements")
  private JsonArray requiredAchievements;

  @JsonProperty("skills")
  private JsonArray skills;

  @JsonProperty("attributes")
  private JsonObject attributes;

  public CreateEducationModuleRequest() {
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public int getStudyYear() {
    return studyYear;
  }

  public void setStudyYear(int studyYear) {
    this.studyYear = studyYear;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
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

  public JsonObject getAttributes() {
    return attributes;
  }

  public void setAttributes(JsonObject attributes) {
    this.attributes = attributes;
  }
}
