package io.epos.portal_api.api.admin.dto;

import java.time.LocalDate;

public class StudentGroupDTO {
  private int id;
  private LocalDate startDate;
  private LocalDate endDate;
  private String name;

  private int organisationUnitId;

  private int educationModuleVersionId;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getOrganisationUnitId() {
    return organisationUnitId;
  }

  public void setOrganisationUnitId(int organisationUnitId) {
    this.organisationUnitId = organisationUnitId;
  }

  public int getEducationModuleVersionId() {
    return educationModuleVersionId;
  }

  public void setEducationModuleVersionId(int educationModuleVersionId) {
    this.educationModuleVersionId = educationModuleVersionId;
  }
}
