package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.util.LogUtils;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.NoSuchElementException;

public class EducationModuleRepository {
  private final Mutiny.SessionFactory emf;

  public EducationModuleRepository(Mutiny.SessionFactory emf) {
    this.emf = emf;
  }

  Uni<EducationModule> getEducationModule(int id) {
    return emf.withSession(session ->
      session.find(EducationModule.class, id)
        .onItem().ifNull().failWith(new NoSuchElementException(LogUtils.NO_EDUCATION_MODULE_WITH_ID_MESSAGE.buildMessage(id))));
  }

  Uni<List<EducationModule>> listEducationModules() {
    return emf.withSession(session -> session.createQuery("from EducationModule", EducationModule.class).getResultList());
  }

  Uni<EducationModule> createEducationModule(EducationModule educationModule) {
    return emf.withTransaction(session ->
      session.persist(educationModule)
      .call(session::flush)
      .replaceWith(educationModule));
  }
}
