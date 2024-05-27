package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.educationModule.EducationModuleController;
import io.epos.portal_api.api.educationModule.EducationModuleValidationHandler;
import io.vertx.ext.web.handler.LoggerFormat;
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
    adminRouter.get("/admin/company").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::readAll).handler(adminController::getCompanies);
    adminRouter.post("/admin/company").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(validationHandler::create).handler(adminController::createCompany);
    return adminRouter;
  }
}
