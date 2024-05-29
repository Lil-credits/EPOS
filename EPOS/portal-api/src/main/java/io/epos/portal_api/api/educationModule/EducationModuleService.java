package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.dto.EducationModuleListResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleMapper;
import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleVersionResponseDTO;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.QueryUtils;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
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

  public Uni<EducationModuleListResponseDTO> getEducationModules(String p, String l) {
    int page = QueryUtils.getPage(p);
    int limit = QueryUtils.getLimit(l);
    int offset = QueryUtils.getOffset(page, limit);

        Uni<List<EducationModule>> educationModulesUni = emf.withSession(session -> repository.listEducationModules(session, limit, offset));
        Uni<Long> totalUni = emf.withSession(repository::countEducationModules);

        return Uni.combine().all().unis(educationModulesUni, totalUni).asTuple().
          onItem().transform(tuple -> {
            List<EducationModuleResponseDTO> moduleDTOs = tuple.getItem1().stream()
              .map(EducationModuleMapper::toDTO)
              .collect(Collectors.toList());
            int total = tuple.getItem2().intValue();
            return new EducationModuleListResponseDTO(total, page, limit, moduleDTOs);
          });
  }

  public Uni<EducationModuleVersionResponseDTO> createEducationModule(EducationModule educationModule, EducationModuleVersion educationModuleVersion) {
    educationModuleVersion.setEducationModule(educationModule);
    return emf.withTransaction(session -> repository.createEducationModule(session, educationModuleVersion)).map(EducationModuleMapper::toDTO);
  }
}
