package io.epos.portal_api.api.admin.dto;

import io.vertx.core.json.JsonObject;

public class MembershipDTO {
  private int id;
  private OrganisationalUnitDTO organisationalUnit;

  private AccountDTO account;

  private JsonObject issuanceKey;

  private String did;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public OrganisationalUnitDTO getOrganisationalUnit() {
    return organisationalUnit;
  }

  public void setOrganisationalUnit(OrganisationalUnitDTO organisationalUnit) {
    this.organisationalUnit = organisationalUnit;
  }

  public AccountDTO getAccount() {
    return account;
  }

  public void setAccount(AccountDTO account) {
    this.account = account;
  }

  public JsonObject getIssuanceKey() {
    return issuanceKey;
  }

  public void setIssuanceKey(JsonObject issuanceKey) {
    this.issuanceKey = issuanceKey;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }
}
