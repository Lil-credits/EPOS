package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleVersionResponseDTO;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;

import java.util.stream.Collectors;

public class EducationModuleMapper {
  public static EducationModuleResponseDTO toDTO(EducationModule module) {
    if (module == null) {
      return null;
    }

    EducationModuleResponseDTO dto = new EducationModuleResponseDTO();
    dto.setId(module.getId());
    dto.setVersions(
      module.getEducationModuleVersions().stream()
        .map(EducationModuleMapper::toDTO)
        .collect(Collectors.toList())
    );
    return dto;
  }

  public static EducationModuleVersionResponseDTO toDTO(EducationModuleVersion version) {
    if (version == null) {
      return null;
    }

    EducationModuleVersionResponseDTO dto = new EducationModuleVersionResponseDTO();
    dto.setId(version.getId());
    dto.setName(version.getName());
    dto.setEffectuationDate(version.getEffectuationDate());
    dto.setBaseCredential(version.getBaseCredential());
    return dto;
  }
}
