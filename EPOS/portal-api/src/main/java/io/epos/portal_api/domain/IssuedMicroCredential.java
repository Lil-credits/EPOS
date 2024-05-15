package io.epos.portal_api.domain;

import io.vertx.core.json.JsonObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class IssuedMicroCredential {
  private int id;
  private int issuer;
  private int receiver;
  private String status;

  private JsonObject credential;
  private Timestamp issuedTimestamp;
  private int educationModuleVersionId;

  public IssuedMicroCredential() {
  }

  public IssuedMicroCredential(int issuer, int receiver, int educationModuleVersionId, JsonObject credential) {
    this.issuer = issuer;
    this.receiver = receiver;
    this.educationModuleVersionId = educationModuleVersionId;
    this.issuedTimestamp = Timestamp.valueOf(LocalDateTime.now());
    this.credential = credential;
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

  public JsonObject getCredential() {
    return credential;
  }

  public void setCredential(JsonObject credential) {
    this.credential = credential;
  }

  public Timestamp getIssuedTimestamp() {
    return issuedTimestamp;
  }

  public void setIssuedTimestamp(Timestamp issuedTimestamp) {
    this.issuedTimestamp = issuedTimestamp;
  }
}

