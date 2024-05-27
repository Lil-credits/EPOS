package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.admin.dto.AdminMapper;
import io.epos.portal_api.api.admin.dto.CompanyDTO;
import io.epos.portal_api.api.admin.dto.SubsidiaryDTO;
import io.epos.portal_api.domain.Company;
import io.epos.portal_api.domain.Subsidiary;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
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

  public Uni<SubsidiaryDTO> createSubsidiary(Subsidiary subsidiary, int companyId) {
    return emf.withTransaction(session -> repository.getCompany(session, companyId)
      .onItem().transformToUni(company -> {
        subsidiary.setCompany(company);
        return repository.createSubsidiary(session, subsidiary)
          .call(session::flush) // Ensure the session is flushed to generate the ID
          .onItem().transform(AdminMapper::toDTO);
      }));
  }

  public Uni<List<SubsidiaryDTO>> getSubsidiaries() {
    return emf.withSession(repository::getSubsidiaries).map(subsidiaries -> subsidiaries.stream()
      .map(AdminMapper::toDTO)
      .collect(Collectors.toList()));
  }
}
