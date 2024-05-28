package io.epos.portal_api.api.admin;

import io.epos.portal_api.domain.*;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.NoSuchElementException;

public class AdminRepository {
  public Uni<Company> createCompany(Mutiny.Session session, Company company) {
    return session.persist(company).replaceWith(company);
  }

  public Uni<Company> getCompany(Mutiny.Session session, int id) {
    return session.find(Company.class, id)
      .onItem().ifNull().failWith(new NoSuchElementException("Company with id " + id + " not found"));
  }

  public Uni<List<Company>> getCompanies(Mutiny.Session session) {
    String query = "SELECT c FROM Company c";

    return session.createQuery(query, Company.class)
      .getResultList();
  }

  public Uni<List<Subsidiary>> getSubsidiaries(Mutiny.Session session) {
    String query = "SELECT s FROM Subsidiary s";

    return session.createQuery(query, Subsidiary.class)
      .getResultList();
  }

  public Uni<Subsidiary> createSubsidiary(Mutiny.Session session, Subsidiary subsidiary) {
    return session.persist(subsidiary).replaceWith(subsidiary);
  }

  public Uni<OrganisationalUnit> createOrganisationUnit(Mutiny.Session session, OrganisationalUnit organisationalUnit) {
    return session.persist(organisationalUnit).replaceWith(organisationalUnit);
  }

  public Uni<Subsidiary> getSubsidiary(Mutiny.Session session, int subsidiaryId) {
    return session.find(Subsidiary.class, subsidiaryId)
      .onItem().ifNull().failWith(new NoSuchElementException("Subsidiary with id " + subsidiaryId + " not found"));
  }

  public Uni<List<OrganisationalUnit>> getOrganisationUnits(Mutiny.Session session) {
    String query = "SELECT o FROM OrganisationalUnit o";

    return session.createQuery(query, OrganisationalUnit.class)
      .getResultList();
  }

  public Uni<OrganisationalUnit> getOrganisationalUnit(Mutiny.Session session, Integer organisationUnitId) {
    return session.find(OrganisationalUnit.class, organisationUnitId)
      .onItem().ifNull().failWith(new NoSuchElementException("Organisational unit with id " + organisationUnitId + " not found"));
  }

  public Uni<List<EducationModule>> getEducationModules(Mutiny.Session session) {
    String query = "SELECT em FROM EducationModule em LEFT JOIN FETCH em.educationModuleVersions";

    return session.createQuery(query, EducationModule.class)
      .getResultList();
  }

    public Uni<Account> createAccount(Mutiny.Session session, Account account) {
      return session.persist(account).replaceWith(account);
    }

  public Uni<List<Account>> getAccounts(Mutiny.Session session) {
    String query = "SELECT a FROM Account a";

    return session.createQuery(query, Account.class)
      .getResultList();
  }

  public Uni<Account> getAccount(Mutiny.Session session, Integer accountId) {
    return session.find(Account.class, accountId)
      .onItem().ifNull().failWith(new NoSuchElementException("Account with id " + accountId + " not found"));
  }

  public Uni<Membership> createMembership(Mutiny.Session session, Membership membership) {
    return session.persist(membership).replaceWith(membership);
  }

  public Uni<List<Membership>> getMemberships(Mutiny.Session session) {
    String query = "SELECT m FROM Membership m";

    return session.createQuery(query, Membership.class)
      .getResultList();
  }

  public Uni<List<StudentGroup>> getClasses(Mutiny.Session session) {
    String query = "SELECT sg FROM StudentGroup sg";

    return session.createQuery(query, StudentGroup.class)
      .getResultList();
  }

  public Uni<StudentGroup> createClass(Mutiny.Session session, StudentGroup studentGroup) {
    return session.persist(studentGroup).replaceWith(studentGroup);
  }
}


