package io.epos.portal_api.api.admin.dto;

public class MembershipDTO {
  private int id;
  private OrganisationalUnitDTO organisationalUnit;

  private AccountDTO account;

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
}
