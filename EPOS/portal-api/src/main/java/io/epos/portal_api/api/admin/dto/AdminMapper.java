package io.epos.portal_api.api.admin.dto;

import io.epos.portal_api.domain.*;

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

  public static OrganisationalUnitDTO toDTO(OrganisationalUnit organisationalUnit) {
    if (organisationalUnit == null) {
      return null;
    }

    OrganisationalUnitDTO dto = new OrganisationalUnitDTO();
    dto.setId(organisationalUnit.getId());
    dto.setName(organisationalUnit.getName());
    return dto;
  }

  public static AccountDTO toDTO(Account account) {
    if (account == null) {
      return null;
    }

    AccountDTO dto = new AccountDTO();
    dto.setId(account.getId());
    dto.setName(account.getName());
    return dto;
  }


  public static MembershipDTO toDTO(Membership membership) {
    if (membership == null) {
      return null;
    }

    MembershipDTO dto = new MembershipDTO();
    dto.setId(membership.getId());
    dto.setOrganisationalUnit(toDTO(membership.getOrganisationalUnit()));
    dto.setAccount(toDTO(membership.getAccount()));
    dto.setIssuanceKey(membership.getIssuanceKey());
    dto.setDid(membership.getDid());
    return dto;
  }

  public static StudentGroupDTO toDTO(StudentGroup studentGroup) {
    if (studentGroup == null) {
      return null;
    }

    StudentGroupDTO dto = new StudentGroupDTO();
    dto.setId(studentGroup.getId());
    System.out.println(studentGroup.getName());
    dto.setName(studentGroup.getName());
    dto.setStartDate(studentGroup.getStartDate());
    dto.setEndDate(studentGroup.getEndDate());
    dto.setOrganisationUnitId(studentGroup.getOrganisationalUnit().getId());
    dto.setEducationModuleVersionId(studentGroup.getEducationModuleVersion().getId());
    return dto;
  }

//  public static CreateClassRequest toCreateClassRequest(JsonObject body) {
//    CreateClassRequest createClassRequest = new CreateClassRequest();
//    createClassRequest.setName(body.getString("name"));
//    createClassRequest.
//
//  }
}
