package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.ApiComponentFactory;
import io.epos.portal_api.api.repository.ImageRepository;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public class EducationModuleFactory implements ApiComponentFactory {

  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    EducationModuleRepository repository = new EducationModuleRepository();
    ImageRepository imageRepository = new ImageRepository();
    EducationModuleService service = new EducationModuleService(repository, imageRepository, emf);
    EducationModuleController controller = new EducationModuleController(service);
    EducationModuleValidationHandler validationHandler = new EducationModuleValidationHandler(vertx);
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, controller, validationHandler);
    educationModuleRouter.setRouter(router);
  }
}
