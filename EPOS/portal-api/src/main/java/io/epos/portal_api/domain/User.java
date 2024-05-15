package io.epos.portal_api.domain;

import io.vertx.core.json.JsonObject;

public class User {
  private final int id;
  private final String name;
  private final String did;
  private final JsonObject issuanceKey;

  public User(int id, String name, String did, JsonObject issuanceKey) {
    this.id = id;
    this.name = name;
    this.did = did;
    this.issuanceKey = issuanceKey;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDid() {
    return did;
  }

  public JsonObject getIssuanceKey() {
    return issuanceKey;
  }
}
