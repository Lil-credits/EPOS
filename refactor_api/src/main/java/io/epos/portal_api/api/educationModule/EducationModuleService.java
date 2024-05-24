package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.QueryUtils;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class EducationModuleService {
  private final EducationModuleRepository repository;
  private final Mutiny.SessionFactory emf;

  public EducationModuleService(EducationModuleRepository repository, Mutiny.SessionFactory emf) {
    this.repository = repository;
    this.emf = emf;
  }

  public Uni<EducationModuleResponseDTO> getEducationModule(int id) {
    return emf.withSession(session -> repository.getEducationModule(session, id)
        .map(EducationModuleMapper::toDTO));
  }

  public Uni<List<EducationModuleResponseDTO>> getEducationModules(String p, String l) {
    int page = QueryUtils.getPage(p);
    int limit = QueryUtils.getLimit(l);
    int offset = QueryUtils.getOffset(page, limit);
    return emf.withSession(session -> repository.listEducationModules(session, limit, offset))
      .map(modules -> modules.stream()
      .map(EducationModuleMapper::toDTO)
      .collect(Collectors.toList()));
  }

  public Uni<EducationModuleVersion> createEducationModule(EducationModule educationModule, EducationModuleVersion educationModuleVersion) {
    educationModuleVersion.setEducationModule(educationModule);
    return emf.withTransaction(session -> repository.createEducationModule(session, educationModuleVersion));
  }
}
