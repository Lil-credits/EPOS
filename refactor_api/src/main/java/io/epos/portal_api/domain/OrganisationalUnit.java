package io.epos.portal_api.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "organisational_units")
public class OrganisationalUnit {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @ManyToOne
  @JoinColumn(name = "subsidiary_id")
  private Subsidiary subsidiary;

  @OneToMany(mappedBy = "organisationalUnit")
  private List<EducationModule> educationModules;

  @OneToMany(mappedBy = "organisationalUnit")
  private List<Membership> memberships;

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

  public List<EducationModule> getEducationModules() {
    return educationModules;
  }

  public void setEducationModules(List<EducationModule> educationModules) {
    this.educationModules = educationModules;
  }

  public List<Membership> getMemberships() {
    return memberships;
  }

  public void setMemberships(List<Membership> memberships) {
    this.memberships = memberships;
  }
}
