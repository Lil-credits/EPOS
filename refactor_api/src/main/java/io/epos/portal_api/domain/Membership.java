package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

  @ManyToOne
  @JoinColumn(name = "invited_by_membership_id")
  private Membership invitedBy;

  @OneToMany(mappedBy = "invitedBy")
  private Set<Membership> invitedMembers;

  @OneToMany(mappedBy = "issuerMembership")
  private List<IssuedCredential> issuedCredentials;

  @OneToMany(mappedBy = "subjectMembership")
  private List<IssuedCredential> receivedCredentials;

  @OneToMany(mappedBy = "membership")
  private List<EducationModuleVersion> educationModuleVersions;
}
