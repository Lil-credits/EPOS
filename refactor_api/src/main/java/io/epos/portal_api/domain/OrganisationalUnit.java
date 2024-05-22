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
}
