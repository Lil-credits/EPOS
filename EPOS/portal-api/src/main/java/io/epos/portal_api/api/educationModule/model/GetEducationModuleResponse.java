package io.epos.portal_api.api.educationModule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;

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

  @JsonProperty("educationModuleVersions")
  private List<EducationModuleVersion> educationModuleVersions;

  public GetEducationModuleResponse() {
  }
  public GetEducationModuleResponse(EducationModule educationModule) {
    this.id = educationModule.getId();
    this.name = educationModule.getName();
    this.imageUrl = educationModule.getImageUrl();
    this.teamId = educationModule.getTeamId();
    this.educationModuleVersions = educationModule.getEducationModuleVersions();
  }
}
