package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subsidiaries")
public class Subsidiary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.ALL)
  private List<EducationModule> educationModules;

  @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.ALL)
  private List<OrganisationalUnit> organisationalUnits;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

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
    organisationalUnits = organisationalUnits;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
