package io.epos.portal_api.domain;


import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "did")
  private String did;

  @Column(name = "issuance_key")
  private JsonObject issuanceKey;

  @OneToMany(mappedBy = "account")
  private List<Membership> memberships;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public JsonObject getIssuanceKey() {
    return issuanceKey;
  }

  public void setIssuanceKey(JsonObject issuanceKey) {
    this.issuanceKey = issuanceKey;
  }

  public List<Membership> getMemberships() {
    return memberships;
  }

  public void setMemberships(List<Membership> memberships) {
    this.memberships = memberships;
  }
}
