package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.common.exception.NotFoundException;
import io.epos.portal_api.domain.*;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Repository class for handling micro credential related database operations.
 */
public class MicroCredentialRepository {
  private static final Logger logger = LoggerFactory.getLogger(MicroCredentialRepository.class);

  /**
   * Retrieves a membership by its ID.
   *
   * @param session  The Hibernate session.
   * @param memberId The ID of the membership.
   * @return A Uni emitting the membership or an error if not found.
   */
  public Uni<Membership> getMembership(Mutiny.Session session, int memberId) {
    logger.debug("Fetching membership with ID {}", memberId);
    return session.find(Membership.class, memberId)
      .onItem().ifNull().failWith(() -> {
        logger.error("No membership found with ID {}", memberId);
        return new NotFoundException("No membership with ID " + memberId);
      });
  }

  /**
   * Retrieves an education module version by its ID.
   *
   * @param session                   The Hibernate session.
   * @param educationModuleVersionId  The ID of the education module version.
   * @return A Uni emitting the education module version or an error if not found.
   */
  public Uni<EducationModuleVersion> getEducationModuleVersion(Mutiny.Session session, int educationModuleVersionId) {
    logger.debug("Fetching education module version with ID {}", educationModuleVersionId);
    return session.find(EducationModuleVersion.class, educationModuleVersionId)
      .onItem().ifNull().failWith(() -> {
        logger.error("No education module version found with ID {}", educationModuleVersionId);
        return new NotFoundException("No education module version with ID " + educationModuleVersionId);
      });
  }

  /**
   * Creates a new issued credential.
   *
   * @param session          The Hibernate session.
   * @param issuedCredential The issued credential to be created.
   * @return A Uni emitting the created issued credential.
   */
  public Uni<IssuedCredential> createIssuedCredential(Mutiny.Session session, IssuedCredential issuedCredential) {
    logger.debug("Creating issued credential for subject ID {}", issuedCredential.getSubjectAccount().getId());
    return session.persist(issuedCredential)
      .replaceWith(issuedCredential)
      .invoke(() -> logger.info("Issued credential created for subject ID {}", issuedCredential.getSubjectAccount().getId()));
  }

  /**
   * Retrieves an account by its ID.
   *
   * @param session   The Hibernate session.
   * @param subjectId The ID of the account.
   * @return A Uni emitting the account or an error if not found.
   */
  public Uni<Account> getAccount(Mutiny.Session session, int subjectId) {
    logger.debug("Fetching account with ID {}", subjectId);
    return session.find(Account.class, subjectId)
      .onItem().ifNull().failWith(() -> {
        logger.error("No account found with ID {}", subjectId);
        return new NotFoundException("No account with ID " + subjectId);
      });
  }

    public Uni<List<IssuedCredential>> getIssuedCredentialsByMembershipId(Mutiny.Session session, Integer membershipId) {
      logger.debug("Fetching issued credentials for membership ID {}", membershipId);
      String query = "SELECT ic FROM IssuedCredential ic WHERE ic.issuerMembership.id = :membershipId";
      return session.createQuery(query, IssuedCredential.class)
        .setParameter("membershipId", membershipId)
        .getResultList();
    }

  public Uni<List<Membership>> getMembersByOrganisationalUnitId(Mutiny.Session session, Integer organisationalUnitId) {
    logger.debug("Fetching members for organisational unit ID {}", organisationalUnitId);
    String query = "SELECT m FROM Membership m WHERE m.organisationalUnit.id = :organisationalUnitId";
    return session.createQuery(query, Membership.class)
      .setParameter("organisationalUnitId", organisationalUnitId)
      .getResultList();
  }

  public Uni<OrganisationalUnit> getOrganisationalUnit(Mutiny.Session session, Integer organisationalUnitId) {
    return session.find(OrganisationalUnit.class, organisationalUnitId)
      .onItem().ifNull().failWith(() -> {
        logger.error("No organisational unit found with ID {}", organisationalUnitId);
        return new NotFoundException("No organisational unit with ID " + organisationalUnitId);
      });
  }

  public Uni<List<IssuedCredential>> getIssuedCredentialsByOrganisationalUnitId(Mutiny.Session session, Integer organisationalUnitId) {
    return session.createQuery(
        "SELECT ic FROM IssuedCredential ic " +
          "JOIN ic.issuerMembership m " +
          "JOIN m.organisationalUnit ou " +
          "WHERE ou.id = :organisationalUnitId", IssuedCredential.class)
      .setParameter("organisationalUnitId", organisationalUnitId)
      .getResultList();
  }
}
