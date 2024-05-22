package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subsidiaries")
public class Subsidiary {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "subsidiary")
  private List<EducationModule> educationModules;
  @OneToMany(mappedBy = "subsidiary")
  private List<OrganisationalUnit> OrganisationalUnits;
  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
