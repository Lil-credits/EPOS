package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.admin.dto.AdminMapper;
import io.epos.portal_api.api.admin.dto.CompanyDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleMapper;
import io.epos.portal_api.domain.Company;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class AdminService {

  private final AdminRepository repository;
  private final Mutiny.SessionFactory emf;

  public AdminService(AdminRepository repository, Mutiny.SessionFactory emf) {
    this.repository = repository;
    this.emf = emf;
  }

  public Uni<Company> createCompany(Company company) {
    return emf.withTransaction(session -> repository.createCompany(session, company));
  }



  public Uni<List<CompanyDTO>> getCompanies() {
    return emf.withSession(repository::getCompanies).map(companies -> companies.stream()
      .map(AdminMapper::toDTO)
      .collect(Collectors.toList()));
  }
}
