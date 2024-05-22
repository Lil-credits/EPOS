package io.epos.portal_api.domain;

import io.epos.portal_api.util.db.JsonArrayConverter;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @Column(nullable = false)
  private Float price;

  @Column(name = "base_credential")
  private JsonObject baseCredential;

  @Column(name = "properties")
  @Convert(converter = JsonArrayConverter.class)
  private JsonArray properties;

  public JsonObject getBaseCredential() {
    return baseCredential;
  }

  public void setBaseCredential(JsonObject baseCredential) {
    this.baseCredential = baseCredential;
  }

  public JsonArray getProperties() {
    return properties;
  }

  public void setProperties(JsonArray properties) {
    this.properties = properties;
  }
  public Product() {
  }

  public Product(JsonObject productJson) {
    this.setName(productJson.getString("name"));
    this.setPrice(productJson.getFloat("price"));
    this.setBaseCredential(productJson.getJsonObject("baseCredential"));
    this.setProperties(productJson.getJsonArray("properties"));
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Float getPrice() {
    return price;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
