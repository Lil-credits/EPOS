package io.epos.portal_api.integration.waltid;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

public class WaltidClient {

  private final int PORT = 7002;

  private final String HOST;

  private final String REQUESTURI = "/openid4vc/jwt/issue";
  private final WebClient client;

  public WaltidClient(Vertx vertx) {
    this.client = WebClient.create(vertx);

    if (Boolean.parseBoolean(System.getenv("DOCKER"))) {
      this.HOST = "docker-compose-issuer-api-1";
    } else {
      this.HOST = "localhost";
    }
  }

  public Uni<String> issue(JsonObject microCredential) {
    return client.post(PORT, HOST, REQUESTURI)
      .sendJson(microCredential)
      .onItem().transformToUni(response -> {
        if (response.statusCode() == 500) {
          return Uni.createFrom().failure(new RuntimeException("Failed to issue micro credential, " + response.bodyAsString()));
        } else if (response.statusCode() == 502) {
          return Uni.createFrom().failure(new RuntimeException("Failed to issue micro credential, Server is down."));
        } else {
          String qrCodeLink = response.bodyAsString();
          return Uni.createFrom().item(qrCodeLink);
        }
      })
      .onFailure().recoverWithUni(throwable -> Uni.createFrom().failure(new RuntimeException(throwable.getMessage())));
  }
}
