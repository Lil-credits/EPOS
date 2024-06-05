package io.epos.portal_api.api.microCredential.dto;

import io.epos.portal_api.domain.IssuedCredential;

public class MicroCredentialMapper {

  public static IssuedCredentialDTO toIssuedCredentialDTO(IssuedCredential issuedCredential) {
    IssuedCredentialDTO dto = new IssuedCredentialDTO();
    dto.setId(issuedCredential.getId());
    dto.setEducationModuleVersionId(issuedCredential.getEducationModuleVersion().getId());
    dto.setIssuerMembershipId(issuedCredential.getIssuerMembership().getId());
    dto.setSubjectAccountId(issuedCredential.getSubjectAccount().getId());
    dto.setCreatedAt(issuedCredential.getCreatedAt());
    dto.setCredential(issuedCredential.getCredential());
    return dto;
  }
}
