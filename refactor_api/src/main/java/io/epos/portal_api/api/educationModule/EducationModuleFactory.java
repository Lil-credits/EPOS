package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.ApiComponentFactory;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public class EducationModuleFactory implements ApiComponentFactory {

  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    EducationModuleRepository repository = new EducationModuleRepository();
    EducationModuleService service = new EducationModuleService(emf, repository);
    EducationModuleHandler handler = new EducationModuleHandler(service);
    EducationModuleValidationHandler validationHandler = new EducationModuleValidationHandler(vertx);
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, handler, validationHandler);
    educationModuleRouter.setRouter(router);
  }
}
