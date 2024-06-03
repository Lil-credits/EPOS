package io.epos.portal_api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "account_class")
public class AccountStudentGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "student_group_id", nullable = false)
  private StudentGroup studentGroup;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public StudentGroup getStudentGroup() {
    return studentGroup;
  }

  public void setStudentGroup(StudentGroup studentGroup) {
    this.studentGroup = studentGroup;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}
