package io.epos.portal_api.api.common;

/**
 * A utility class that holds common request parameter names used in the application.
 */
public class RequestParameters {

  /**
   * The request parameter name for an ID.
   */
  public static final String ID_PARAMETER = "id";

  /**
   * The request parameter name for a page number.
   */
  public static final String PAGE_PARAMETER = "page";

  /**
   * The request parameter name for a limit on the number of items.
   */
  public static final String LIMIT_PARAMETER = "limit";

  // Private constructor to prevent instantiation
  private RequestParameters() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
}
