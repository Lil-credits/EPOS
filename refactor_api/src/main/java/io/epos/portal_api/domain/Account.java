package io.epos.portal_api.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(mappedBy = "account")
  private List<Membership> memberships;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
