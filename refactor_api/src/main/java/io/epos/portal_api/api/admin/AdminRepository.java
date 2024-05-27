package io.epos.portal_api.api.admin;

import io.epos.portal_api.domain.Company;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.OrganisationalUnit;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.stream.Collectors;

public class AdminRepository {
  public Uni<Company> createCompany(Mutiny.Session session, Company company) {
    return session.persist(company).replaceWith(company);
  }

  public Uni<List<Company>> getCompanies(Mutiny.Session session) {
    String query = "SELECT c FROM Company c";

    return session.createQuery(query, Company.class)
      .getResultList();
  }
}


