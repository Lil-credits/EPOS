package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "memberships")
public class Membership {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "organisational_unit_id")
  private OrganisationalUnit organisationalUnit;

  @Column(name = "invited_by_membership_id")
  private Integer invitedByMembershipId;

  @OneToMany(mappedBy = "issuerMembership")
  private List<IssuedCredential> issuedCredentials;

  @OneToMany(mappedBy = "subjectMembership")
  private List<IssuedCredential> receivedCredentials;

  @OneToMany(mappedBy = "membership")
  private List<EducationModuleVersion> educationModuleVersions;
}
