package io.epos.portal_api.domain;

public class IssuedMicroCredential {
  private int id;
  private int issuer;
  private int receiver;
  private String status;
  private int educationModuleVersionId;

  public IssuedMicroCredential() {
  }

  public IssuedMicroCredential(int id, int issuer, int receiver, String status, int educationModuleVersionId) {
    this.id = id;
    this.issuer = issuer;
    this.receiver = receiver;
    this.status = status;
    this.educationModuleVersionId = educationModuleVersionId;
  }

  // Getters and Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIssuer() {
    return issuer;
  }

  public void setIssuer(int issuer) {
    this.issuer = issuer;
  }

  public int getReceiver() {
    return receiver;
  }

  public void setReceiver(int receiver) {
    this.receiver = receiver;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getEducationModuleVersionId() {
    return educationModuleVersionId;
  }

  public void setEducationModuleVersionId(int educationModuleVersionId) {
    this.educationModuleVersionId = educationModuleVersionId;
  }

  // toString() method

  @Override
  public String toString() {
    return "IssuedMicroCredential{" +
      "id=" + id +
      ", issuer=" + issuer +
      ", receiver=" + receiver +
      ", status='" + status + '\'' +
      ", educationModuleVersionId=" + educationModuleVersionId +
      '}';
  }
}

