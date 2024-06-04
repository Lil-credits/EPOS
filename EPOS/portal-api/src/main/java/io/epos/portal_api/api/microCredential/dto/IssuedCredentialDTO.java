package io.epos.portal_api.api.microCredential.dto;

import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;

public class IssuedCredentialDTO {

  private int id;
  private int educationModuleVersionId;
  private int issuerMembershipId;
  private int subjectAccountId;
  private LocalDateTime createdAt;
  JsonObject credential;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getEducationModuleVersionId() {
    return educationModuleVersionId;
  }

  public void setEducationModuleVersionId(int educationModuleVersionId) {
    this.educationModuleVersionId = educationModuleVersionId;
  }

  public int getIssuerMembershipId() {
    return issuerMembershipId;
  }

  public void setIssuerMembershipId(int issuerMembershipId) {
    this.issuerMembershipId = issuerMembershipId;
  }

  public int getSubjectAccountId() {
    return subjectAccountId;
  }

  public void setSubjectAccountId(int subjectAccountId) {
    this.subjectAccountId = subjectAccountId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public JsonObject getCredential() {
    return credential;
  }

  public void setCredential(JsonObject credential) {
    this.credential = credential;
  }
}
