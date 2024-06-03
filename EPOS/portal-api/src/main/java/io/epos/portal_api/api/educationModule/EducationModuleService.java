package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.educationModule.dto.EducationModuleListResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleMapper;
import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleVersionResponseDTO;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.Image;
import io.epos.portal_api.util.QueryUtils;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling education module-related business logic.
 */
public class EducationModuleService {
  private final EducationModuleRepository repository;
  private final Mutiny.SessionFactory emf;

  /**
   * Constructs an EducationModuleService with the given dependencies.
   *
   * @param repository      EducationModuleRepository instance.
   * @param emf             Hibernate session factory.
   */
  public EducationModuleService(EducationModuleRepository repository, Mutiny.SessionFactory emf) {
    this.repository = repository;
    this.emf = emf;
  }

  /**
   * Retrieves an education module by its ID.
   *
   * @param id ID of the education module to retrieve.
   * @return A Uni that emits the education module response DTO.
   */
  public Uni<EducationModuleResponseDTO> getEducationModule(int id) {
    return emf.withSession(session -> repository.getEducationModule(session, id)
      .map(EducationModuleMapper::toDTO));
  }

  /**
   * Retrieves a list of education modules with pagination.
   *
   * @param p Page number.
   * @param l Limit per page.
   * @return A Uni that emits the education module list response DTO.
   */
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

  /**
   * Creates a new education module.
   *
   * @param educationModule       Education module entity.
   * @param educationModuleVersion Education module version entity.
   * @param image                 Image entity.
   * @return A Uni that emits the education module version response DTO.
   */
  public Uni<EducationModuleVersionResponseDTO> createEducationModule(EducationModule educationModule, EducationModuleVersion educationModuleVersion, Image image) {
    educationModuleVersion.setEducationModule(educationModule);
    educationModuleVersion.setImage(image);
    return emf.withTransaction(session -> repository.createEducationModule(session, educationModuleVersion)).map(EducationModuleMapper::toDTO);
  }
}
