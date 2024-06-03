package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "education_modules")
public class EducationModule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(optional = false)
  @JoinColumn(name = "subsidiary_id", nullable = false)
  private Subsidiary subsidiary;

  @ManyToOne(optional = false)
  @JoinColumn(name = "organisational_unit_id", nullable = false)
  private OrganisationalUnit organisationalUnit;

  @OneToMany(mappedBy = "educationModule", cascade = CascadeType.ALL, orphanRemoval = true) // Cascade type all for education module versions
  private List<EducationModuleVersion> educationModuleVersions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Subsidiary getSubsidiary() {
    return subsidiary;
  }

  public void setSubsidiary(Subsidiary subsidiary) {
    this.subsidiary = subsidiary;
  }

  public OrganisationalUnit getOrganisationalUnit() {
    return organisationalUnit;
  }

  public void setOrganisationalUnit(OrganisationalUnit organisationalUnit) {
    this.organisationalUnit = organisationalUnit;
  }

  public List<EducationModuleVersion> getEducationModuleVersions() {
    return educationModuleVersions;
  }

  public void setEducationModuleVersions(List<EducationModuleVersion> educationModuleVersions) {
    this.educationModuleVersions = educationModuleVersions;
  }

}
