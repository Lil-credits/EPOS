package io.epos.portal_api.domain;


import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;

@Entity
@Table(name = "issued_credentials")
public class IssuedCredential {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "credential")
  private JsonObject credential;

  // @Column(name = "created_at")

  @ManyToOne
  @JoinColumn(name = "education_module_version_id")
  private EducationModuleVersion educationModuleVersion;

  @ManyToOne
  @JoinColumn(name = "issuer_membership_id")
  private Membership issuerMembership;

  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Membership subjectMembership;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public JsonObject getCredential() {
    return credential;
  }

  public void setCredential(JsonObject credential) {
    this.credential = credential;
  }

  public EducationModuleVersion getEducationModuleVersion() {
    return educationModuleVersion;
  }

  public void setEducationModuleVersion(EducationModuleVersion educationModuleVersion) {
    this.educationModuleVersion = educationModuleVersion;
  }

  public Membership getIssuerMembership() {
    return issuerMembership;
  }

  public void setIssuerMembership(Membership issuerMembership) {
    this.issuerMembership = issuerMembership;
  }

  public Membership getSubjectMembership() {
    return subjectMembership;
  }

  public void setSubjectMembership(Membership subjectMembership) {
    this.subjectMembership = subjectMembership;
  }
}
