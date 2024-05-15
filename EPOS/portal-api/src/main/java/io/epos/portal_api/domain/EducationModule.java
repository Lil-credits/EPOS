package io.epos.portal_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.sqlclient.templates.annotations.Column;

import java.util.ArrayList;
import java.util.List;

public class EducationModule {

  private Integer id;

  private String name;

  private String imageUrl;

  private Integer teamId;

  private List<EducationModuleVersion> educationModuleVersions = new ArrayList<>();


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

  @Override
  public String toString() {
    return "EducationModule{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", imageUrl='" + imageUrl + '\'' +
      ", teamId=" + teamId +
      ", educationModuleVersions=" + educationModuleVersions +
      '}';
  }
}
