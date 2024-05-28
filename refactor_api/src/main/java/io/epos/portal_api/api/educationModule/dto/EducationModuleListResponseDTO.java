package io.epos.portal_api.api.educationModule.dto;

import java.util.List;

public class EducationModuleListResponseDTO {

  private int total;

  private int limit;

  private int page;

  private List<EducationModuleResponseDTO> educationModules;

  public EducationModuleListResponseDTO() {
  }

  public EducationModuleListResponseDTO(int total, int page, int limit, List<EducationModuleResponseDTO> educationModules) {
    this.total = total;
    this.page = page;
    this.limit = limit;
    this.educationModules = educationModules;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<EducationModuleResponseDTO> getEducationModules() {
    return educationModules;
  }

  public void setEducationModules(List<EducationModuleResponseDTO> educationModules) {
    this.educationModules = educationModules;
  }
}

