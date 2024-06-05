package io.epos.portal_api.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) // Cascade deletion of memberships
  private List<Membership> memberships;

  @OneToMany(mappedBy = "subjectAccount", cascade = CascadeType.ALL) // Cascade deletion of received credentials
  private List<IssuedCredential> receivedCredentials;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) // Cascade deletion of account-student group associations
  private List<AccountStudentGroup> accountStudentGroups;

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

  public List<Membership> getMemberships() {
    return memberships;
  }

  public void setMemberships(List<Membership> memberships) {
    this.memberships = memberships;
  }

  public List<IssuedCredential> getReceivedCredentials() {
    return receivedCredentials;
  }

  public void setReceivedCredentials(List<IssuedCredential> receivedCredentials) {
    this.receivedCredentials = receivedCredentials;
  }

  public List<AccountStudentGroup> getAccountStudentGroups() {
    return accountStudentGroups;
  }

  public void setAccountStudentGroups(List<AccountStudentGroup> accountStudentGroups) {
    this.accountStudentGroups = accountStudentGroups;
  }
}
