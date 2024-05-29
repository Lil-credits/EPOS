package io.epos.portal_api.configuration;

public class AppConfiguration {
  // Database configurations
  private static final String DB_HOST = System.getenv("DB_HOST");
  private static final String DB_PORT = System.getenv("DB_PORT");
  private static final String DB_NAME = System.getenv("DB_NAME");
  private static final String DB_USER = System.getenv("DB_USER");
  private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

  // Walt.id configurations
  private static final String WALTID_HOST = System.getenv("WALTID_HOST");
  private static final String WALTID_PORT = System.getenv("WALTID_PORT");

  // Getters for database configurations
  public static String getDbHost() {
    return DB_HOST;
  }

  public static String getDbPort() {
    return DB_PORT;
  }

  public static String getDbName() {
    return DB_NAME;
  }

  public static String getDbUser() {
    return DB_USER;
  }

  public static String getDbPassword() {
    return DB_PASSWORD;
  }

  // Getters for Walt.id configurations
  public static String getWaltIdHost() {
    return WALTID_HOST;
  }

  public static String getWaltIdPort() {
    return WALTID_PORT;
  }
}
