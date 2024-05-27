package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.ApiComponentFactory;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public class MicroCredentialFactory implements ApiComponentFactory {
  @Override
  public void create(Vertx vertx, Router router, Mutiny.SessionFactory emf) {
    MicroCredentialRepository repository = new MicroCredentialRepository();
    MicroCredentialService service = new MicroCredentialService(repository, emf);
    MicroCredentialValidationHandler validationHandler = new MicroCredentialValidationHandler(vertx);
    MicroCredentialController controller = new MicroCredentialController(service);
    MicroCredentialRouter microCredentialRouter = new MicroCredentialRouter(vertx, validationHandler, controller);
    microCredentialRouter.setRouter(router);
  }
}
