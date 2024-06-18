// Imports
package io.epos.portal_api.integration.waltid;

import io.epos.portal_api.configuration.AppConfiguration;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

/**
 * Client for interacting with the Walt.id service to issue micro credentials and perform onboarding.
 */
public class WaltidClient {

  private final String port;
  private final String host;
  private final String issueUri = "/openid4vc/jwt/issue";
  private final String onboardUri = "/onboard/issuer";
  private final WebClient client;

  /**
   * Constructor for the WaltidClient.
   *
   * @param vertx Vert.x instance
   */
  public WaltidClient(Vertx vertx) {
//    this.port = AppConfiguration.getWaltIdPort();
//    this.host = AppConfiguration.getWaltIdHost();
    this.port = "7002";
    this.host = "localhost";
    this.client = WebClient.create(vertx);
  }
  /**
   * Sends a request to issue a micro credential.
   *
   * @param microCredential The micro credential to be issued
   * @return A Uni emitting the QR code link upon successful issuance
   */
  public Uni<String> issue(JsonObject microCredential) {
    return sendRequest(issueUri, microCredential);
  }
  /**
   * Sends a request to perform onboarding.
   *
   * @return A Uni emitting the response from the onboarding request
   */
  public Uni<JsonObject> onboard() {
    JsonObject requestBody = createOnboardRequestBody();
    return sendRequest(onboardUri, requestBody)
      .onItem().transform(this::processOnboardResponse);
  }

  /**
   * Sends a request with the given URI and request body.
   *
   * @param uri The URI of the request
   * @param requestBody The request body in JSON format
   * @return A Uni emitting the response body as a string
   */
  private Uni<String> sendRequest(String uri, JsonObject requestBody) {
    return client.post(Integer.parseInt(port), host, uri)
      .sendJson(requestBody)
      .onItem().transformToUni(response -> {
        if (response.statusCode() == 500) {
          return Uni.createFrom().failure(new RuntimeException("Failed to execute request, " + response.bodyAsString()));
        } else if (response.statusCode() == 502) {
          return Uni.createFrom().failure(new RuntimeException("Failed to execute request, Server is down."));
        } else {
          return Uni.createFrom().item(response.bodyAsString());
        }
      });
  }
  /**
   * Creates a JSON object for onboarding with default configurations.
   *
   * @return The JSON object for onboarding request body
   */
  private JsonObject createOnboardRequestBody() {
    return new JsonObject()
      .put("key", new JsonObject()
        .put("backend", "jwk")
        .put("keyType", "secp256r1"))
      .put("did", new JsonObject()
        .put("method", "jwk"));
  }

  /**
   * Processes the response from the onboarding request.
   *
   * @param responseBody The response body in JSON format
   * @return The modified JSON object with encoded "jwk" field in the "issuanceKey" object
   */
  private JsonObject processOnboardResponse(String responseBody) {
    JsonObject onboardResponse = new JsonObject(responseBody);
    JsonObject issuanceKey = onboardResponse.getJsonObject("issuerKey");
    issuanceKey.put("jwk", issuanceKey.getJsonObject("jwk").encode());
    onboardResponse.put("issuerKey", issuanceKey);
    return onboardResponse;
  }

  public static void main(String[] args) {
    WaltidClient client = new WaltidClient(Vertx.vertx());
    client.onboard().subscribe().with(
      response -> System.out.println("Onboarding response: "),
      error -> System.out.println("Error: " + error.getMessage())
    );
  }
}
