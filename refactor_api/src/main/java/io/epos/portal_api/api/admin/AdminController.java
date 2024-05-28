package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.common.ResponseBuilder;
import io.epos.portal_api.api.educationModule.EducationModuleController;
import io.epos.portal_api.domain.*;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminController {
  private static final Logger logger = LoggerFactory.getLogger(EducationModuleController.class);
  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  public void createCompany(RoutingContext routingContext) {
    logger.info("Creating company");
    JsonObject body = routingContext.body().asJsonObject();
    Company company = new Company();
    company.setName(body.getString("name"));
    adminService.createCompany(company).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );

  }

  public void getCompanies(RoutingContext routingContext) {
    logger.info("Getting companies");
    adminService.getCompanies().subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }


  public void createSubsidiary(RoutingContext routingContext) {
    logger.info("Creating subsidiary");
    JsonObject body = routingContext.body().asJsonObject();
    Subsidiary subsidiary = new Subsidiary();
    subsidiary.setName(body.getString("name"));
    adminService.createSubsidiary(subsidiary, body.getInteger("companyId")).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }

  public void getSubsidiaries(RoutingContext routingContext) {
    logger.info("Getting subsidiaries");
    adminService.getSubsidiaries().subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }
  public void createOrganisationUnit(RoutingContext routingContext) {
    logger.info("Creating organisation unit");
    JsonObject body = routingContext.body().asJsonObject();
    OrganisationalUnit organisationalUnit = new OrganisationalUnit();
    organisationalUnit.setName(body.getString("name"));

    adminService.createOrganisationUnit(organisationalUnit, body.getInteger("companyId"), body.getInteger("subsidiaryId")).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }

  public void getOrganisationUnits(RoutingContext routingContext) {
    logger.info("Getting organisation units");
    adminService.getOrganisationUnits().subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }
//
//  public void createMember(RoutingContext routingContext) {
//    logger.info("Creating member");
//    adminService.createMember().subscribe().with(
//      result -> ResponseBuilder.buildOkResponse(routingContext, result),
//      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
//    );
//  }
//
//  public void getMembers(RoutingContext routingContext) {
//    logger.info("Getting members");
//    adminService.getMembers().subscribe().with(
//      result -> ResponseBuilder.buildOkResponse(routingContext, result),
//      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
//    );
//  }
//
  public void createEducationModule(RoutingContext routingContext){
    logger.info("Creating education module");
    JsonObject body = routingContext.body().asJsonObject();
    EducationModule educationModule = new EducationModule();
    EducationModuleVersion educationModuleVersion = new EducationModuleVersion();
    educationModuleVersion.setName(body.getString("name"));
    educationModuleVersion.setBaseCredential(body.getJsonObject("baseCredential"));
    adminService.createEducationModule(educationModule, educationModuleVersion, body.getInteger("organisationalUnitId")).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }

  public void getEducationModules(RoutingContext routingContext) {
    logger.info("Getting education modules");
    adminService.getEducationModules().subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }

  public void createMember(RoutingContext routingContext) {
    logger.info("Creating member");
    JsonObject body = routingContext.body().asJsonObject();
  }

    public void createAccount(RoutingContext routingContext) {
    logger.info("Creating account");
    JsonObject body = routingContext.body().asJsonObject();
    adminService.createAccount(body.getString("name")).subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );


    }

  public void getAccounts(RoutingContext routingContext) {
    logger.info("Getting accounts");
    adminService.getAccounts().subscribe().with(
      result -> ResponseBuilder.buildOkResponse(routingContext, result),
      error -> ResponseBuilder.buildErrorResponse(routingContext, error)
    );
  }
}
