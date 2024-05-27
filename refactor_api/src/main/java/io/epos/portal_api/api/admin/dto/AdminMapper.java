package io.epos.portal_api.api.admin.dto;

import io.epos.portal_api.domain.Company;

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
}
