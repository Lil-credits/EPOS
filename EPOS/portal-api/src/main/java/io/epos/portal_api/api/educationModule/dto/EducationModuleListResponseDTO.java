package io.epos.portal_api.api.educationModule.dto;

import java.util.List;

/**
 * DTO class representing a response for a list of education modules.
 */
public class EducationModuleListResponseDTO {
  private int total;
  private int limit;
  private int page;
  private List<EducationModuleResponseDTO> educationModules;

  /**
   * Default constructor for EducationModuleListResponseDTO.
   */
  public EducationModuleListResponseDTO() {
  }

  /**
   * Constructs an EducationModuleListResponseDTO with the given parameters.
   *
   * @param total            Total number of education modules.
   * @param page             Page number.
   * @param limit            Limit per page.
   * @param educationModules List of education modules.
   */
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
