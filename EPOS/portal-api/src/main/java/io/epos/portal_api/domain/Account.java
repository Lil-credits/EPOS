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

  @OneToMany(mappedBy = "account")
  private List<Membership> memberships;

  @OneToMany(mappedBy = "subjectAccount")
  private List<IssuedCredential> receivedCredentials;

  @ManyToMany
  @JoinTable(
    name = "account_class",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "class_id")
  )
  private List<StudentGroup> studentGroups;

  // Getters and setters

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

  public List<StudentGroup> getStudentGroups() {
    return studentGroups;
  }

  public void setStudentGroups(List<StudentGroup> studentGroups) {
    this.studentGroups = studentGroups;
  }

  public List<IssuedCredential> getReceivedCredentials() {
    return receivedCredentials;
  }

  public void setReceivedCredentials(List<IssuedCredential> receivedCredentials) {
    this.receivedCredentials = receivedCredentials;
  }
}
