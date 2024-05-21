package io.epos.portal_api.domain;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "educationmoduleversion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationModuleVersion {

  @Column
  @Id
  @GeneratedValue
  private int id;

  @Column
  private int version;

  @Column
  private String description;

  @Column
  private JsonObject attributes;

  @Column(name = "requiredachievements")
  private JsonArray requiredAchievements;
  @Column
  private JsonArray skills;

  @Column(name = "educationmoduleid")
  private int educationModuleID;
  private String status;
}
