package io.epos.portal_api.api.common.exception;

/**
 * Exception thrown when a bad request is made to the server.
 * This typically corresponds to a 400 Bad Request HTTP status code.
 */
public class BadRequestException extends HttpRequestException {

  /**
   * Constructs a new BadRequestException with the specified detail message.
   *
   * @param message the detail message
   */
  public BadRequestException(String message) {
    super(message, 400);
  }
}
