package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.integration.waltid.WaltidClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.JsonSchema;
import io.vertx.sqlclient.Pool;

import static io.epos.portal_api.util.FileUtils.readJsonObject;
import static io.epos.portal_api.util.FileUtils.readJsonSchema;

public class MicroCredentialService {

  private final Pool dbClient;
  private final MicroCredentialRepository microCredentialRepository;
  private final WaltidClient waltidClient;

  public MicroCredentialService(Pool dbClient, MicroCredentialRepository microCredentialRepository, WaltidClient waltidClient) {
    this.dbClient = dbClient;
    this.microCredentialRepository = microCredentialRepository;
    this.waltidClient = waltidClient;
  }
  public Future<String> issue(int educationModuleId, int userId) {
    JsonObject validMicroCredential = readJsonObject("micro_credential.json");
    Promise<String> promise = Promise.promise();
    waltidClient.issue(validMicroCredential).onComplete(ar -> {
      if (ar.succeeded()) {
        String qrCodeLink = ar.result();
        promise.complete(qrCodeLink);
      } else {
        Throwable cause = ar.cause();
        promise.fail(cause);
      }
    });

    return promise.future();
  }
}
