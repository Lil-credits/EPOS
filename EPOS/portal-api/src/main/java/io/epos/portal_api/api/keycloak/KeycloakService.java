package io.epos.portal_api.api.keycloak;

import io.epos.portal_api.configuration.AppConfiguration;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.Cookie;
import io.vertx.mutiny.ext.auth.User;
import io.vertx.mutiny.ext.auth.oauth2.OAuth2Auth;
import io.vertx.mutiny.ext.auth.oauth2.providers.KeycloakAuth;
import io.vertx.mutiny.ext.web.RoutingContext;


/**
 * Service class for handling Keycloak-related operations.
 */
public class KeycloakService {
  private final OAuth2Auth oauth2;

  public KeycloakService(Vertx vertx) {
    JsonObject keycloakJson = new JsonObject()
      .put("realm", "micro-credentials-dev")
      .put("auth-server-url", "https://iam.web3connect.nl/")
      .put("ssl-required", "external")
      .put("resource", "frontend")
      .put("verify-token-audience", true)
      .put("credentials", new JsonObject()
        .put("secret", AppConfiguration.getKeycloakSecret()));

    this.oauth2 = KeycloakAuth.create(vertx, OAuth2FlowType.PASSWORD, keycloakJson);
  }

  public Uni<User> authenticate(String username, String password) {
    return oauth2.authenticate(new JsonObject()
        .put("username", username)
        .put("password", password));
  }

  public void setTokens(RoutingContext ctx, User user) {
    String accessToken = user.principal().getString("access_token");
    Integer expiresIn = user.principal().getInteger("expires_in");
    ctx.response().addCookie(Cookie.cookie("accessToken", accessToken)
      .setPath("/")
      .setHttpOnly(true)
      .setMaxAge(expiresIn));

    String refreshToken = user.principal().getString("refresh_token");
    Integer refreshExpiresIn = user.principal().getInteger("refresh_expires_in");
    ctx.response().addCookie(Cookie.cookie("refreshToken", refreshToken)
      .setPath("/")
      .setHttpOnly(true)
      .setMaxAge(refreshExpiresIn));
  }

  public Uni<Void> secureEndpoints(RoutingContext ctx) {
    Cookie accessTokenCookie = ctx.request().getCookie("accessToken");
    if (accessTokenCookie == null) {
      Cookie refreshTokenCookie = ctx.request().getCookie("refreshToken");
      if (refreshTokenCookie != null) {
        return refreshAndAuthenticate(ctx, refreshTokenCookie.getValue());
      } else {
        return Uni.createFrom().failure(new Throwable("Not authorized"));
      }
    } else {
      return authenticate(ctx, accessTokenCookie.getValue());
    }
  }

  private Uni<Void> refreshAndAuthenticate(RoutingContext ctx, String refreshToken) {
    User user = User.create(new JsonObject().put("refresh_token", refreshToken));
    return oauth2.refresh(user)
      .onItem().transformToUni(refreshedUser -> {
        setTokens(ctx, refreshedUser);
        return oauth2.authenticate(new JsonObject().put("access_token", refreshedUser.principal().getString("access_token")));
      })
      .onItem().transformToUni(theUser -> {
        ctx.setUser(theUser);
        return Uni.createFrom().voidItem();
      });
  }

  private Uni<Void> authenticate(RoutingContext ctx, String accessToken) {
    return oauth2.authenticate(new JsonObject().put("access_token", accessToken))
      .onItem().transformToUni(theUser -> {
        ctx.setUser(theUser);
        return Uni.createFrom().voidItem();
      });
  }

  public boolean isUserInRole(RoutingContext ctx, String role) {
    return ctx.user().principal().getJsonObject("realm_access").getJsonArray("roles").contains(role);
  }

  public JsonObject getUserDetails(RoutingContext ctx) {
    JsonObject response = new JsonObject();
    response.put("email", ctx.user().principal().getString("email"));
    response.put("username", ctx.user().principal().getString("username"));
    response.put("given_name", ctx.user().principal().getString("given_name"));
    response.put("family_name", ctx.user().principal().getString("family_name"));
    return response;
  }
}
