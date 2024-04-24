package io.epos.portal_api;


import io.epos.portal_api.verticle.MainVerticle;
import io.vertx.core.Vertx;

public class Main {

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName())
      .onFailure(throwable -> System.exit(-1));
  }

}
