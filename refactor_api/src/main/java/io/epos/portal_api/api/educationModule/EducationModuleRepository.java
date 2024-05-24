package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.LogUtils;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EducationModuleRepository {
  public Uni<EducationModule> getEducationModule(Mutiny.Session session, int id) {
    return session.find(EducationModule.class, id)
      .onItem().ifNull().failWith(new NoSuchElementException("No education module with ID " + id))
      .call(module ->
        Mutiny.fetch(module.getEducationModuleVersions())
        .replaceWith(module));
  }

  public Uni<List<EducationModule>> listEducationModules(Mutiny.Session session) {
    return session.createQuery("SELECT em FROM EducationModule em LEFT JOIN FETCH em.educationModuleVersions", EducationModule.class)
      .getResultList();
  }


  public Uni<EducationModuleVersion> createEducationModule(Mutiny.Session session, EducationModuleVersion educationModuleVersion) {
    return session.persist(educationModuleVersion.getEducationModule())
      .call(() -> session.persist(educationModuleVersion))
      .replaceWith(educationModuleVersion);
  }
}
