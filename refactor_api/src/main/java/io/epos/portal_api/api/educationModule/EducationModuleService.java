package io.epos.portal_api.api.educationModule;

import org.hibernate.reactive.mutiny.Mutiny;

public class EducationModuleService {

  private final Mutiny.SessionFactory emf;
  private final EducationModuleRepository repository;
  public EducationModuleService(Mutiny.SessionFactory emf, EducationModuleRepository repository) {
    this.emf = emf;
    this.repository = repository;
  }
}
