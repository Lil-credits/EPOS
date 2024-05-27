package io.epos.portal_api.api.admin;

import io.epos.portal_api.api.ApiComponentFactory;
import io.epos.portal_api.api.educationModule.EducationModuleRepository;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public class AdminFactory implements ApiComponentFactory {


  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    AdminRepository repository = new AdminRepository();
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();
    AdminService service = new AdminService(repository, emf, educationModuleRepository );
    AdminController controller = new AdminController(service);
    AdminValidationHandler validationHandler = new AdminValidationHandler(vertx);
    AdminRouter adminRouter = new AdminRouter(vertx, controller, validationHandler);
    adminRouter.setRouter(router);
  }
}
