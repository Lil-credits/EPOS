package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "images")
public class Image {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name="file_name")
  private String fileName;

  @Column(name="image_data")
  private String imageData;

  @Column(name="content_type")
  private String contentType;

  @OneToMany(mappedBy = "image")
  private List<EducationModuleVersion> educationModuleVersions;

//  @Column(name="created_at")

}
