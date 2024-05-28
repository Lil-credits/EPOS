package io.epos.portal_api.domain;

import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "memberships")
public class Membership {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "did")
  private String did;

  @Column(name = "issuance_key")
  private JsonObject issuanceKey;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "organisational_unit_id")
  private OrganisationalUnit organisationalUnit;

  @ManyToOne
  @JoinColumn(name = "invited_by_membership_id")
  private Membership invitedBy;

  @OneToMany(mappedBy = "invitedBy")
  private Set<Membership> invitedMembers;

  @OneToMany(mappedBy = "issuerMembership")
  private List<IssuedCredential> issuedCredentials;

  @OneToMany(mappedBy = "subjectMembership")
  private List<IssuedCredential> receivedCredentials;

  @OneToMany(mappedBy = "membership")
  private List<EducationModuleVersion> educationModuleVersions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public JsonObject getIssuanceKey() {
    return issuanceKey;
  }

  public void setIssuanceKey(JsonObject issuanceKey) {
    this.issuanceKey = issuanceKey;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public OrganisationalUnit getOrganisationalUnit() {
    return organisationalUnit;
  }

  public void setOrganisationalUnit(OrganisationalUnit organisationalUnit) {
    this.organisationalUnit = organisationalUnit;
  }

  public Membership getInvitedBy() {
    return invitedBy;
  }

  public void setInvitedBy(Membership invitedBy) {
    this.invitedBy = invitedBy;
  }

  public Set<Membership> getInvitedMembers() {
    return invitedMembers;
  }

  public void setInvitedMembers(Set<Membership> invitedMembers) {
    this.invitedMembers = invitedMembers;
  }

  public List<IssuedCredential> getIssuedCredentials() {
    return issuedCredentials;
  }

  public void setIssuedCredentials(List<IssuedCredential> issuedCredentials) {
    this.issuedCredentials = issuedCredentials;
  }

  public List<IssuedCredential> getReceivedCredentials() {
    return receivedCredentials;
  }

  public void setReceivedCredentials(List<IssuedCredential> receivedCredentials) {
    this.receivedCredentials = receivedCredentials;
  }

  public List<EducationModuleVersion> getEducationModuleVersions() {
    return educationModuleVersions;
  }

  public void setEducationModuleVersions(List<EducationModuleVersion> educationModuleVersions) {
    this.educationModuleVersions = educationModuleVersions;
  }
}
