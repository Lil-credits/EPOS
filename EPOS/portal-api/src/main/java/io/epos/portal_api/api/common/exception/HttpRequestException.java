package io.epos.portal_api.api.common.exception;

/**
 * Abstract base class for HTTP request exceptions.
 * This class is intended to be extended by specific HTTP request exception classes.
 */
public abstract class HttpRequestException extends Exception {

  private final int statusCode;

  /**
   * Constructs a new HttpRequestException with the specified detail message and HTTP status code.
   *
   * @param message    the detail message
   * @param statusCode the HTTP status code
   */
  public HttpRequestException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  /**
   * Returns the HTTP status code associated with this exception.
   *
   * @return the HTTP status code
   */
  public int getStatusCode() {
    return statusCode;
  }
}
