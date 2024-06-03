package io.epos.portal_api.api.educationModule.dto;

import java.util.List;

/**
 * DTO class representing a response for an education module.
 */
public class EducationModuleResponseDTO {
  private int id;
  private List<EducationModuleVersionResponseDTO> versions;

  /**
   * Retrieves the ID of the education module.
   *
   * @return The ID of the education module.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID of the education module.
   *
   * @param id The ID of the education module.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retrieves the list of versions associated with the education module.
   *
   * @return The list of versions associated with the education module.
   */
  public List<EducationModuleVersionResponseDTO> getVersions() {
    return versions;
  }

  /**
   * Sets the list of versions associated with the education module.
   *
   * @param versions The list of versions associated with the education module.
   */
  public void setVersions(List<EducationModuleVersionResponseDTO> versions) {
    this.versions = versions;
  }
}
