package io.epos.portal_api.integration.waltid;

import io.epos.portal_api.domain.IssuedMicroCredential;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

public class WaltidClient {

  private final int PORT = 7002;
  private final String HOST = "localhost";
  private final String REQUESTURI = "/openid4vc/jwt/issue";
  private final WebClient client;
  public WaltidClient(Vertx vertx) {
    this.client = WebClient.create(vertx);
  }

  public Future<String> issue(JsonObject microCredential) {
    Promise<String> promise = Promise.promise();

    client.post(PORT, HOST, REQUESTURI)
      .sendJson(microCredential, ar -> {
        if (ar.succeeded()) {
          if (ar.result().statusCode() == 500) {
            promise.fail(new RuntimeException("Failed to issue micro credential," + ar.result().bodyAsString()));
          } else if (ar.result().statusCode() == 502) {
            promise.fail(new RuntimeException("Failed to issue micro credential, Server is down."));
          } else {
            String qrCodeLink = ar.result().bodyAsString();
            promise.complete(qrCodeLink);
          }
        } else {
            promise.fail(new RuntimeException("Failed to issue micro credential, Server request failed."));
          }
      });

    return promise.future();
  }
  public void readAll() {
  }

  public void issueBatch() {
  }
}


