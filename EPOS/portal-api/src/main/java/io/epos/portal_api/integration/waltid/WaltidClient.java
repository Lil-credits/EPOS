package io.epos.portal_api.integration.waltid;

import io.epos.portal_api.configuration.AppConfiguration;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

public class WaltidClient {

  private final String PORT = AppConfiguration.getWaltIdPort();

  private final String HOST = AppConfiguration.getWaltIdHost();

  private final String REQUESTURI_ISSUE = "/openid4vc/jwt/issue";
  private final String REQUEST_URI_ONBOARD = "/onboard/issuer";
  private final WebClient client;

  public WaltidClient(Vertx vertx) {
    this.client = WebClient.create(vertx);
  }

  public Uni<String> issue(JsonObject microCredential) {
    return client.post(Integer.parseInt(PORT), HOST, REQUESTURI_ISSUE)
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
  public Uni<JsonObject> onboard(){
    JsonObject requestBody = (JsonObject) Json.decodeValue("{\"issuanceKeyConfig\": {\"type\": \"local\",\"algorithm\": \"secp256r1\"},\"issuerDidConfig\": {\"method\": \"jwk\"}}");
//    return Uni.createFrom().item(requestBody);
    return client.post(Integer.parseInt(PORT), HOST, REQUEST_URI_ONBOARD)
    .sendJson(requestBody).onItem().transform(response -> {
    if (response.statusCode() == 500) {
      throw new RuntimeException("Failed to onboard, " + response.bodyAsString());
    } else if (response.statusCode() == 502) {
      throw new RuntimeException("Failed to onboard, Server is down.");
    } else {
      JsonObject onboardResponse = response.bodyAsJsonObject();
      // Get the "issuanceKey" object
      JsonObject issuanceKey = onboardResponse.getJsonObject("issuanceKey");
      // Encode the "jwk" field within the "issuanceKey" object
      String encodedJwk = issuanceKey.getJsonObject("jwk").encode();
      // Replace the original "jwk" object with the encoded string in the "issuanceKey" object
      issuanceKey.put("jwk", encodedJwk);
      // Put the modified "issuanceKey" object back into the response
      onboardResponse.put("issuanceKey", issuanceKey);
      // Encode the entire response object to a JSON string
      return onboardResponse;
    }
  })
    .onFailure().recoverWithUni(throwable -> Uni.createFrom().failure(new RuntimeException(throwable.getMessage())));
}
}
