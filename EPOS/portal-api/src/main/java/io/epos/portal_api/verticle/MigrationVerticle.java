package io.epos.portal_api.verticle;

import io.epos.portal_api.util.DbUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;

public class MigrationVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> promise) {
    Configuration config = DbUtils.buildMigrationsConfiguration();
    Flyway flyway = new Flyway(config);
    flyway.migrate();
    promise.complete();
  }

}
