package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.ApiComponentFactory;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

/**
 * Factory class responsible for creating components related to education modules.
 */
public class EducationModuleFactory implements ApiComponentFactory {

  /**
   * Creates and configures education module components.
   *
   * @param vertx   Vert.x instance.
   * @param router  Router instance to configure routes.
   * @param emf     Hibernate session factory.
   */
  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    // Create repositories
    EducationModuleRepository educationModuleRepository = new EducationModuleRepository();

    // Create service and inject repositories
    EducationModuleService educationModuleService = new EducationModuleService(educationModuleRepository, emf);

    // Create controller and inject service
    EducationModuleController educationModuleController = new EducationModuleController(educationModuleService);

    // Create validation handler
    EducationModuleValidationHandler validationHandler = new EducationModuleValidationHandler(vertx);

    // Create router and set up routes
    EducationModuleRouter educationModuleRouter = new EducationModuleRouter(vertx, educationModuleController, validationHandler);
    educationModuleRouter.setRouter(router);
  }
}
