package io.epos.portal_api.api.microCredential.dto;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Data Transfer Object (DTO) representing the base credential for a course.
 */
public class BaseCredential {

  private String courseName;
  private int studyYear;
  private String description;
  private String imageUrl;
  private JsonArray requiredAchievements;
  private JsonArray skills;
  private JsonObject attributes;

  /**
   * Gets the course name.
   *
   * @return the course name
   */
  public String getCourseName() {
    return courseName;
  }

  /**
   * Sets the course name.
   *
   * @param courseName the course name
   */
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  /**
   * Gets the study year.
   *
   * @return the study year
   */
  public int getStudyYear() {
    return studyYear;
  }

  /**
   * Sets the study year.
   *
   * @param studyYear the study year
   */
  public void setStudyYear(int studyYear) {
    this.studyYear = studyYear;
  }

  /**
   * Gets the description of the course.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the course.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the image URL of the course.
   *
   * @return the image URL
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets the image URL of the course.
   *
   * @param imageUrl the image URL
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Gets the required achievements for the course.
   *
   * @return the required achievements
   */
  public JsonArray getRequiredAchievements() {
    return requiredAchievements;
  }

  /**
   * Sets the required achievements for the course.
   *
   * @param requiredAchievements the required achievements
   */
  public void setRequiredAchievements(JsonArray requiredAchievements) {
    this.requiredAchievements = requiredAchievements;
  }

  /**
   * Gets the skills associated with the course.
   *
   * @return the skills
   */
  public JsonArray getSkills() {
    return skills;
  }

  /**
   * Sets the skills associated with the course.
   *
   * @param skills the skills
   */
  public void setSkills(JsonArray skills) {
    this.skills = skills;
  }

  /**
   * Gets the additional attributes for the course.
   *
   * @return the attributes
   */
  public JsonObject getAttributes() {
    return attributes;
  }

  /**
   * Sets the additional attributes for the course.
   *
   * @param attributes the attributes
   */
  public void setAttributes(JsonObject attributes) {
    this.attributes = attributes;
  }
}
