package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;
import java.util.List;
import java.util.NoSuchElementException;

public class EducationModuleRepository {
  public Uni<EducationModule> getEducationModule(Mutiny.Session session, int id) {
    return session.find(EducationModule.class, id)
      .onItem().ifNull().failWith(new NoSuchElementException("No education module with ID " + id))
      .call(module ->
        Mutiny.fetch(module.getEducationModuleVersions())
          .replaceWith(module));
  }

  public Uni<List<EducationModule>> listEducationModules(Mutiny.Session session, int limit, int offset) {
    String query = "SELECT em FROM EducationModule em LEFT JOIN FETCH em.educationModuleVersions";

    return session.createQuery(query, EducationModule.class)
      .setFirstResult(offset)
      .setMaxResults(limit)
      .getResultList();
  }

  public Uni<Long> countEducationModules(Mutiny.Session session) {
    String query = "SELECT COUNT(em) FROM EducationModule em";

    return session.createQuery(query, Long.class)
      .getSingleResult();
  }

  public Uni<EducationModuleVersion> createEducationModule(Mutiny.Session session, EducationModuleVersion educationModuleVersion) {
    return session.persist(educationModuleVersion.getEducationModule())
      .chain(() -> session.persist(educationModuleVersion.getImage()))
      .chain(() -> session.persist(educationModuleVersion))
      .replaceWith(educationModuleVersion);
  }
}
