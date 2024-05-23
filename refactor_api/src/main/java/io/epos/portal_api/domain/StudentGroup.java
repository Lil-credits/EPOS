package io.epos.portal_api.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="class")
public class StudentGroup {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String name;

  @Column(name="start_date")
  private Date startDate;

  @Column(name="end_date")
  private Date endDate;


}
