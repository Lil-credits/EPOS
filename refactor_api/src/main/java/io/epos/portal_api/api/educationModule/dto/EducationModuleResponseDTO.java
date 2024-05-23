package io.epos.portal_api.api.educationModule.dto;

import java.util.List;

public class EducationModuleResponseDTO {
  private int id;
  private List<EducationModuleVersionResponseDTO> versions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<EducationModuleVersionResponseDTO> getVersions() {
    return versions;
  }

  public void setVersions(List<EducationModuleVersionResponseDTO> versions) {
    this.versions = versions;
  }
}
