package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "education_modules")
public class EducationModule {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @ManyToOne
  @JoinColumn(name = "subsidiary_id")
  private Subsidiary subsidiary;

  @ManyToOne
  @JoinColumn(name = "organisational_unit_id")
  private OrganisationalUnit organisationalUnit;

  @OneToMany(mappedBy = "educationModule")
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
