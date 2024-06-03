package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private List<Subsidiary> subsidiaries;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private List<EducationModule> educationModules;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private List<OrganisationalUnit> organisationalUnits;

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

  public List<Subsidiary> getSubsidiaries() {
    return subsidiaries;
  }

  public void setSubsidiaries(List<Subsidiary> subsidiaries) {
    this.subsidiaries = subsidiaries;
  }

  public List<EducationModule> getEducationModules() {
    return educationModules;
  }

  public void setEducationModules(List<EducationModule> educationModules) {
    this.educationModules = educationModules;
  }

  public List<OrganisationalUnit> getOrganisationalUnits() {
    return organisationalUnits;
  }

  public void setOrganisationalUnits(List<OrganisationalUnit> organisationalUnits) {
    this.organisationalUnits = organisationalUnits;
  }
}
