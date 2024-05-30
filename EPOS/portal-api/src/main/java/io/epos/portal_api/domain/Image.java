package io.epos.portal_api.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "image_data", columnDefinition = "TEXT")
  private String imageData;

  @Column(name = "content_type")
  private String contentType;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "image")
  private List<EducationModuleVersion> educationModuleVersions;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  // Getters and setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public List<EducationModuleVersion> getEducationModuleVersions() {
    return educationModuleVersions;
  }

  public void setEducationModuleVersions(List<EducationModuleVersion> educationModuleVersions) {
    this.educationModuleVersions = educationModuleVersions;
  }
}
