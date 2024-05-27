package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.admin.dto.AdminMapper;
import io.epos.portal_api.api.admin.dto.CompanyDTO;
import io.epos.portal_api.api.admin.dto.OrganisationalUnitDTO;
import io.epos.portal_api.api.admin.dto.SubsidiaryDTO;
import io.epos.portal_api.api.educationModule.EducationModuleRepository;
import io.epos.portal_api.api.educationModule.dto.EducationModuleMapper;
import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleVersionResponseDTO;
import io.epos.portal_api.domain.*;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class AdminService {

  private final AdminRepository repository;
  private final Mutiny.SessionFactory emf;

  private final EducationModuleRepository educationModuleRepository;

  public AdminService(AdminRepository repository, Mutiny.SessionFactory emf, EducationModuleRepository educationModuleRepository) {
    this.repository = repository;
    this.emf = emf;
    this.educationModuleRepository = educationModuleRepository;
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

  public Uni<List<OrganisationalUnitDTO>> getOrganisationUnits() {
    return emf.withSession(repository::getOrganisationUnits)
      .map(organisationalUnits -> organisationalUnits.stream().map(AdminMapper::toDTO)
      .collect(Collectors.toList()));
  }

  public Uni<OrganisationalUnitDTO> createOrganisationUnit(OrganisationalUnit organisationalUnit, int companyId, int subsidiaryId) {
    Uni<Company> companyUni = emf.withSession(session -> repository.getCompany(session, companyId));
    Uni<Subsidiary> subsidiaryUni = emf.withSession(session -> repository.getSubsidiary(session, subsidiaryId));

    return Uni.combine().all().unis(companyUni, subsidiaryUni).asTuple()
      .onItem().transformToUni(tuple -> {
        organisationalUnit.setCompany(tuple.getItem1());
        organisationalUnit.setSubsidiary(tuple.getItem2());
        return emf.withTransaction(session ->
          repository.createOrganisationUnit(session, organisationalUnit)
            .call(session::flush)
            .map(AdminMapper::toDTO)
        );
      });
  }

  public Uni<EducationModuleVersionResponseDTO> createEducationModule(EducationModule educationModule, EducationModuleVersion educationModuleVersion, Integer organisationUnitId) {
    Uni<OrganisationalUnit> organisationalUnitUni = emf.withSession(session -> repository.getOrganisationalUnit(session, organisationUnitId));
    return organisationalUnitUni.onItem().transformToUni(organisationalUnit -> {
      educationModule.setOrganisationalUnit(organisationalUnit);
      educationModule.setCompany(organisationalUnit.getCompany());
      educationModule.setSubsidiary(organisationalUnit.getSubsidiary());
      educationModuleVersion.setEducationModule(educationModule);
      return emf.withTransaction(session -> educationModuleRepository.createEducationModule(session, educationModuleVersion))
        .map(EducationModuleMapper::toDTO);
    });
  }

  public Uni<List<EducationModuleResponseDTO>> getEducationModules() {
    return emf.withSession(repository::getEducationModules).
      onItem().transform(educationModules -> educationModules.stream().map(EducationModuleMapper::toDTO).collect(Collectors.toList()));
  }


}
