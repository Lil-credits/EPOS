package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

public class EducationModuleService {

  private final EducationModuleRepository repository;
  public EducationModuleService(EducationModuleRepository repository) {
    this.repository = repository;
  }

  public Uni<EducationModule> getEducationModule(int id) {
    return repository.getEducationModule(id);
  }

  public Uni<List<EducationModule>> getEducationModules() {
    return repository.listEducationModules();
  }

  public Uni<EducationModule> createEducationModule(EducationModule educationModule, EducationModuleVersion educationModuleVersion) {
    educationModule.setEducationModuleVersions(List.of(educationModuleVersion));
    return repository.createEducationModule(educationModule);
  }


}
