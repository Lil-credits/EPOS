package io.epos.portal_api.api.common.exception;

/**
 * Exception thrown when a path parameter is invalid.
 * This typically corresponds to a 400 Bad Request HTTP status code.
 */
public class InvalidPathParameterException extends BadRequestException {

  /**
   * Constructs a new InvalidPathParameterException with a default detail message.
   */
  public InvalidPathParameterException() {
    super("Invalid path parameter");
  }

  /**
   * Constructs a new InvalidPathParameterException with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidPathParameterException(String message) {
    super(message);
  }
}
