package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.IssuedCredential;
import io.epos.portal_api.domain.Membership;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.NoSuchElementException;

public class MicroCredentialRepository {

    public Uni<Membership> getMembership(Mutiny.Session session, int memberId) {
        return session.find(Membership.class, memberId).onItem().ifNull().failWith(new NoSuchElementException("No membership with ID " + memberId));
    }

  public Uni<EducationModuleVersion> getEducationModuleVersion(Mutiny.Session session, int educationModuleVersionId) {
    return session.find(EducationModuleVersion.class, educationModuleVersionId)
      .onItem().ifNull().failWith(new NoSuchElementException("No education module version with ID " + educationModuleVersionId));
  }

  public Uni<IssuedCredential> createIssuedCredential(Mutiny.Session session, IssuedCredential issuedCredential) {
    return session.persist(issuedCredential).replaceWith(issuedCredential);
  }
}
