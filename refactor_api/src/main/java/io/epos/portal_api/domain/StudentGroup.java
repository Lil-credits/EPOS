package io.epos.portal_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

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
