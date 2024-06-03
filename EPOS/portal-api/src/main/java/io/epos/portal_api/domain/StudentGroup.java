package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "class")
public class StudentGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "organisational_unit_id", nullable = false)
  private OrganisationalUnit organisationalUnit;

  @ManyToOne
  @JoinColumn(name = "education_module_version_id", nullable = false)
  private EducationModuleVersion educationModuleVersion;

  @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
  private List<AccountStudentGroup> studentGroupAccounts;

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

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public OrganisationalUnit getOrganisationalUnit() {
    return organisationalUnit;
  }

  public void setOrganisationalUnit(OrganisationalUnit organisationalUnit) {
    this.organisationalUnit = organisationalUnit;
  }

  public EducationModuleVersion getEducationModuleVersion() {
    return educationModuleVersion;
  }

  public void setEducationModuleVersion(EducationModuleVersion educationModuleVersion) {
    this.educationModuleVersion = educationModuleVersion;
  }

  public List<AccountStudentGroup> getStudentGroupAccounts() {
    return studentGroupAccounts;
  }

  public void setStudentGroupAccounts(List<AccountStudentGroup> studentGroupAccounts) {
    this.studentGroupAccounts = studentGroupAccounts;
  }
}
