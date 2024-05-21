package io.epos.portal_api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "educationmodule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationModule {

  @Id
  @GeneratedValue
  private Integer id;

  @Column
  @NotNull
  @Size(max = 255)
  private String name;

  @Column
  private String imageUrl;

  @Column
  private Integer teamId;
  private String team;
  private String teamFaculty;
  private String teamOrganization;

  private List<EducationModuleVersion> educationModuleVersions = new ArrayList<>();
}
