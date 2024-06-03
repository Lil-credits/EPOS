package io.epos.portal_api.api.educationModule.dto;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;

import java.util.stream.Collectors;

/**
 * Mapper class to convert domain objects to DTOs.
 */
public class EducationModuleMapper {

  /**
   * Converts an EducationModule object to an EducationModuleResponseDTO object.
   *
   * @param module The EducationModule object to be converted.
   * @return The converted EducationModuleResponseDTO object.
   */
  public static EducationModuleResponseDTO toDTO(EducationModule module) {
    if (module == null) {
      return null;
    }

    EducationModuleResponseDTO dto = new EducationModuleResponseDTO();
    dto.setId(module.getId());
    dto.setVersions(module.getEducationModuleVersions().stream()
      .map(EducationModuleMapper::toDTO)
      .collect(Collectors.toList()));
    return dto;
  }

  /**
   * Converts an EducationModuleVersion object to an EducationModuleVersionResponseDTO object.
   *
   * @param version The EducationModuleVersion object to be converted.
   * @return The converted EducationModuleVersionResponseDTO object.
   */
  public static EducationModuleVersionResponseDTO toDTO(EducationModuleVersion version) {
    if (version == null) {
      return null;
    }

    EducationModuleVersionResponseDTO dto = new EducationModuleVersionResponseDTO();
    dto.setId(version.getId());
    dto.setName(version.getName());
    dto.setEffectuationDate(version.getEffectuationDate());
    dto.setBaseCredential(version.getBaseCredential());
    dto.setEducationModuleId(version.getEducationModule().getId());
    if (version.getImage() != null) {
      dto.setImageData(version.getImage().getImageData());
    }
    dto.setCreatedAt(version.getCreatedAt());
    return dto;
  }
}
