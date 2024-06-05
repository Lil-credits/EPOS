package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.ApiComponentFactory;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

/**
 * Factory class for creating and setting up micro credential components.
 */
public class MicroCredentialFactory implements ApiComponentFactory {

  /**
   * Creates and configures the micro credential components.
   *
   * @param vertx  The Vert.x instance to be used.
   * @param router The Router instance to configure the routes.
   * @param emf    The Hibernate reactive session factory.
   */
  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    MicroCredentialRepository repository = new MicroCredentialRepository();
    WaltidClient waltidClient = new WaltidClient(vertx);
    MicroCredentialService service = new MicroCredentialService(repository, emf, waltidClient);
    MicroCredentialValidationHandler validationHandler = new MicroCredentialValidationHandler(vertx);
    MicroCredentialController controller = new MicroCredentialController(service);
    MicroCredentialRouter microCredentialRouter = new MicroCredentialRouter(vertx, validationHandler, controller);

    microCredentialRouter.setRouter(router);
  }
}
