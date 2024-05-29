package io.epos.portal_api.api.admin.dto;

import java.util.List;

public class CreateClassRequest {
  private int organisationalUnitId;
  private String name;

  private int educationModuleVersionId;

  // Getters and setters
  public int getOrganisationalUnitId() {
    return organisationalUnitId;
  }

  public void setOrganisationalUnitId(int organisationalUnitId) {
    this.organisationalUnitId = organisationalUnitId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public int getEducationModuleVersionId() {
    return educationModuleVersionId;
  }

  public void setEducationModuleVersionId(int educationModuleVersionId) {
    this.educationModuleVersionId = educationModuleVersionId;
  }
}
