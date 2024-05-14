package io.epos.portal_api.api.educationModule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class GetEducationModuleResponse {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("imageUrl")
  private String imageUrl;

  @JsonProperty("teamId")
  private Integer teamId;


  @JsonProperty("description")
  private String description;

  @JsonProperty("attributes")
  private JsonObject attributes;
  @JsonProperty("requiredAchievements")
  private JsonArray requiredAchievements;
  @JsonProperty("skills")
  private JsonArray skills;
  @JsonProperty("status")
  private String status;


  public GetEducationModuleResponse() {
  }
  public GetEducationModuleResponse(EducationModule educationModule) {
    this.id = educationModule.getId();
    this.name = educationModule.getName();
    this.imageUrl = educationModule.getImageUrl();
    this.teamId = educationModule.getTeamId();
    // get firxt model version in education module and fill the rest
    List<EducationModuleVersion> educationModuleVersions = educationModule.getEducationModuleVersions();
    if (educationModuleVersions.size() > 0) {
      EducationModuleVersion educationModuleVersion = educationModuleVersions.get(0);
      this.description = educationModuleVersion.getDescription();
      this.attributes = educationModuleVersion.getAttributes();
      this.requiredAchievements = educationModuleVersion.getRequiredAchievements();
      this.skills = educationModuleVersion.getSkills();
      this.status = educationModuleVersion.getStatus();
    }
  }
}
