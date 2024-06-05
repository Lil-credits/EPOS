package io.epos.portal_api.util.db;

import io.epos.portal_api.configuration.AppConfiguration;
import org.hibernate.cfg.AvailableSettings;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for database operations.
 */

public class DbUtil {

  /**
   * Get the Hibernate properties.
   *
   * @return The Hibernate properties.
   */
  public static Map<String, String> getHibernateProperties() {
    Map<String, String> properties = new HashMap<>();
    properties.put(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:postgresql://" + AppConfiguration.getDbHost() + ":" + AppConfiguration.getDbPort() + "/" + AppConfiguration.getDbName());
    properties.put(AvailableSettings.JAKARTA_JDBC_USER, AppConfiguration.getDbUser());
    properties.put(AvailableSettings.JAKARTA_JDBC_PASSWORD, AppConfiguration.getDbPassword());
    properties.put(AvailableSettings.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
    return properties;
  }
}
