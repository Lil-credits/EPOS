package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.admin.dto.*;
import io.epos.portal_api.api.educationModule.EducationModuleRepository;
import io.epos.portal_api.api.educationModule.dto.EducationModuleMapper;
import io.epos.portal_api.api.educationModule.dto.EducationModuleResponseDTO;
import io.epos.portal_api.api.educationModule.dto.EducationModuleVersionResponseDTO;
import io.epos.portal_api.domain.*;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminService {

  private final AdminRepository repository;
  private final Mutiny.SessionFactory emf;

  private final WaltidClient walletClient;

  private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

  private final EducationModuleRepository educationModuleRepository;

  public AdminService(AdminRepository repository, Mutiny.SessionFactory emf, WaltidClient walletClient, EducationModuleRepository educationModuleRepository) {
    this.repository = repository;
    this.emf = emf;
    this.walletClient = walletClient;
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


  public Uni<Account> createAccount(String name) {
    Account account = new Account();
    account.setName(name);
    return emf.withTransaction(session -> repository.createAccount(session, account));
  }

  public Uni<List<AccountDTO>> getAccounts() {
    return emf.withSession(repository::getAccounts).
      onItem().transform(accounts -> accounts.stream().map(AdminMapper::toDTO).collect(Collectors.toList()));
  }

  public Uni<MembershipDTO> createMembership(Integer accountId, Integer organisationalUnitId) {
    return Uni.combine().all().unis(
      emf.withSession(session -> repository.getAccount(session, accountId)),
      emf.withSession(session -> repository.getOrganisationalUnit(session, organisationalUnitId)),
      walletClient.onboard()
    ).asTuple()
      .onItem().transformToUni(tuple -> {
      Membership membership = new Membership();
      membership.setAccount(tuple.getItem1());
      membership.setOrganisationalUnit(tuple.getItem2());
      JsonObject onboardResponse = tuple.getItem3();
      membership.setDid(onboardResponse.getString("issuerDid"));
      membership.setIssuanceKey(onboardResponse.getJsonObject("issuanceKey"));
      return emf.withTransaction(session -> repository.createMembership(session, membership)).map(AdminMapper::toDTO);
    });
  }

  public Uni<List<MembershipDTO>> getMemberships() {
    return emf.withSession(repository::getMemberships).
      onItem().transform(memberships -> memberships.stream().map(AdminMapper::toDTO).collect(Collectors.toList()));
  }

  public Uni<List<StudentGroupDTO>> getClasses() {
    return emf.withSession(repository::getClasses).
      onItem().transform(studentGroups -> studentGroups.stream().map(AdminMapper::toDTO).collect(Collectors.toList()));
  }

  Uni<StudentGroupDTO> createClass(CreateClassRequest request) {
    return emf.withTransaction(session ->
      repository.getOrganisationalUnit(session, request.getOrganisationalUnitId())
        .onItem().transformToUni(organisationalUnit ->
          repository.getEducationModuleVersion(session, request.getEducationModuleVersionId())
            .onItem().transformToUni(educationModuleVersion -> {
              StudentGroup studentGroup = new StudentGroup();
              studentGroup.setName(request.getName());
              studentGroup.setOrganisationalUnit(organisationalUnit);
              studentGroup.setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
              studentGroup.setEducationModuleVersion(educationModuleVersion);
              return repository.createStudentGroup(session, studentGroup)
                .onItem().transform(AdminMapper::toDTO);
            })
        )
    );
  }

  public Uni<Void> addStudentToClass(int classId, int studentId) {
//    return emf.withTransaction(session -> {
//      return repository.getStudentGroup(session, classId)
//        .onItem().transformToUni(studentGroup ->
//          repository.getAccount(session, studentId)
//            .onItem().transform(account -> {
//              account.getStudentGroups().add(studentGroup);
//              studentGroup.getAccounts().add(account);
//              return session.flush().replaceWithVoid();
//            })
//        );
//    });
    return Uni.createFrom().voidItem();
  }


}
