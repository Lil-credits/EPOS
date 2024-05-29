package io.epos.portal_api.api.admin.dto;

import java.util.Date;

public class StudentGroupDTO {
  private int id;
  private Date startDate;
  private Date endDate;
  private String name;

  private int organisationUnitId;

  private int educationModuleVersionId;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
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
