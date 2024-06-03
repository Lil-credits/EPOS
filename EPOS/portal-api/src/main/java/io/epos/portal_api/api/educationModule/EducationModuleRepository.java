package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Repository class for handling CRUD operations related to education modules.
 */
public class EducationModuleRepository {

  /**
   * Retrieves an education module by its ID.
   *
   * @param session Hibernate session.
   * @param id      ID of the education module to retrieve.
   * @return A Uni that emits the retrieved education module.
   */
  public Uni<EducationModule> getEducationModule(Mutiny.Session session, int id) {
    return session.find(EducationModule.class, id)
      .onItem().ifNull().failWith(new NoSuchElementException("No education module with ID " + id))
      .call(module ->
        Mutiny.fetch(module.getEducationModuleVersions())
          .replaceWith(module));
  }

  /**
   * Lists education modules with pagination.
   *
   * @param session Hibernate session.
   * @param limit   Maximum number of education modules to retrieve.
   * @param offset  Offset for pagination.
   * @return A Uni that emits a list of education modules.
   */
  public Uni<List<EducationModule>> listEducationModules(Mutiny.Session session, int limit, int offset) {
    String query = "SELECT em FROM EducationModule em LEFT JOIN FETCH em.educationModuleVersions";

    return session.createQuery(query, EducationModule.class)
      .setFirstResult(offset)
      .setMaxResults(limit)
      .getResultList();
  }

  /**
   * Counts the total number of education modules.
   *
   * @param session Hibernate session.
   * @return A Uni that emits the count of education modules.
   */
  public Uni<Long> countEducationModules(Mutiny.Session session) {
    String query = "SELECT COUNT(em) FROM EducationModule em";

    return session.createQuery(query, Long.class)
      .getSingleResult();
  }

  /**
   * Creates a new education module version.
   *
   * @param session              Hibernate session.
   * @param educationModuleVersion The education module version to create.
   * @return A Uni that emits the created education module version.
   */
  public Uni<EducationModuleVersion> createEducationModule(Mutiny.Session session, EducationModuleVersion educationModuleVersion) {
    return session.persist(educationModuleVersion.getEducationModule())
      .chain(() -> session.persist(educationModuleVersion.getImage()))
      .chain(() -> session.persist(educationModuleVersion))
      .replaceWith(educationModuleVersion);
  }
}
