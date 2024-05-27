package io.epos.portal_api.api.admin.dto;

import io.epos.portal_api.domain.Company;
import io.epos.portal_api.domain.Subsidiary;

public class AdminMapper {

  public static CompanyDTO toDTO(Company company) {
    if (company == null) {
      return null;
    }

    CompanyDTO dto = new CompanyDTO();
    dto.setId(company.getId());
    dto.setName(company.getName());
    return dto;
  }

  public static SubsidiaryDTO toDTO(Subsidiary subsidiary) {
    if (subsidiary == null) {
      return null;
    }

    SubsidiaryDTO dto = new SubsidiaryDTO();
    dto.setId(subsidiary.getId());
    dto.setName(subsidiary.getName());
    return dto;
  }
}
