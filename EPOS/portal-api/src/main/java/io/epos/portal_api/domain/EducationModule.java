package io.epos.portal_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.sqlclient.templates.annotations.Column;

import java.util.List;

public class EducationModule {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("imageurl")
  private String imageUrl;

  @JsonProperty("teamid")
  private Integer teamId;

  @JsonProperty("education_module_versions")
  private List<EducationModuleVersion> educationModuleVersions;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

  public List<EducationModuleVersion> getEducationModuleVersions() {
    return educationModuleVersions;
  }

  public void setEducationModuleVersions(List<EducationModuleVersion> educationModuleVersions) {
    this.educationModuleVersions = educationModuleVersions;
  }
}
