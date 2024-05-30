package io.epos.portal_api.domain;

import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "education_module_versions")
public class EducationModuleVersion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(name = "base_credential")
  private JsonObject baseCredential;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "effectuation_date")
  private LocalDate effectuationDate;

  @OneToMany(mappedBy = "educationModuleVersion")
  private List<StudentGroup> studentGroups;

  @ManyToOne
  @JoinColumn(name = "education_module_id")
  private EducationModule educationModule;

  @ManyToOne
  @JoinColumn(name = "membership_id")
  private Membership membership;

  @ManyToOne()
  @JoinColumn(name = "image_id")
  private Image image;

  @OneToMany(mappedBy = "educationModuleVersion")
  private List<IssuedCredential> issuedCredentials;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  // Getters and setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public JsonObject getBaseCredential() {
    return baseCredential;
  }

  public void setBaseCredential(JsonObject baseCredential) {
    this.baseCredential = baseCredential;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDate getEffectuationDate() {
    return effectuationDate;
  }

  public void setEffectuationDate(LocalDate effectuationDate) {
    this.effectuationDate = effectuationDate;
  }

  public EducationModule getEducationModule() {
    return educationModule;
  }

  public void setEducationModule(EducationModule educationModule) {
    this.educationModule = educationModule;
  }

  public Membership getMembership() {
    return membership;
  }

  public void setMembership(Membership membership) {
    this.membership = membership;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public List<IssuedCredential> getIssuedCredentials() {
    return issuedCredentials;
  }

  public void setIssuedCredentials(List<IssuedCredential> issuedCredentials) {
    this.issuedCredentials = issuedCredentials;
  }

  public List<StudentGroup> getStudentGroups() {
    return studentGroups;
  }

  public void setStudentGroups(List<StudentGroup> studentGroups) {
    this.studentGroups = studentGroups;
  }
}

