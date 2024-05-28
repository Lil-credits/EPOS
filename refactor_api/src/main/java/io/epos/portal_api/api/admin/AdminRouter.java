package io.epos.portal_api.api.admin;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import io.vertx.mutiny.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminRouter {
  private final Vertx vertx;
  private final AdminValidationHandler validationHandler;
  private final AdminController adminController;
  private static final Logger logger = LoggerFactory.getLogger(AdminRouter.class);


  public AdminRouter(Vertx vertx, AdminController adminController, AdminValidationHandler validationHandler) {
    this.vertx = vertx;
    this.adminController = adminController;
    this.validationHandler = validationHandler;

  }

  /**
   * Set education module API routes
   *
   * @param router Router
   */
  public void setRouter(Router router) {
    router.route("/api/v1/*").subRouter(buildAdminRouter());}
  private Router buildAdminRouter() {
    final Router adminRouter = Router.router(vertx);
    adminRouter.route("/admin*").handler(BodyHandler.create());
    adminRouter.route("/admin*").handler(LoggerHandler.create());
    adminRouter.get("/admin/company").handler(validationHandler::readAll).handler(adminController::getCompanies);
    adminRouter.post("/admin/company").handler(validationHandler::createCompany).handler(adminController::createCompany);
    adminRouter.get("/admin/subsidiary").handler(validationHandler::readAll).handler(adminController::getSubsidiaries);
    adminRouter.post("/admin/subsidiary").handler(validationHandler::createSubsidiary).handler(adminController::createSubsidiary);
    adminRouter.get("/admin/organisational-unit").handler(validationHandler::readAll).handler(adminController::getOrganisationUnits);
    adminRouter.post("/admin/organisational-unit").handler(validationHandler::createOrganisationUnit).handler(adminController::createOrganisationUnit);
    adminRouter.get("/admin/education-module").handler(validationHandler::readAll).handler(adminController::getEducationModules);
    adminRouter.post("/admin/education-module").handler(validationHandler::createEducationModule).handler(adminController::createEducationModule);
    adminRouter.get("/admin/account").handler(validationHandler::readAll).handler(adminController::getAccounts);
    adminRouter.post("/admin/account").handler(validationHandler::createAccount).handler(adminController::createAccount);

    return adminRouter;
  }
}
