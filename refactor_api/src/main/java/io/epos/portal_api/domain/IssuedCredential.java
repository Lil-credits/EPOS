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
}
