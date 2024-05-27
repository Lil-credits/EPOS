package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.api.ApiComponentFactory;
import io.epos.portal_api.integration.waltid.WaltidClient;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import org.hibernate.reactive.mutiny.Mutiny;

public class MicroCredentialFactory implements ApiComponentFactory {
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
