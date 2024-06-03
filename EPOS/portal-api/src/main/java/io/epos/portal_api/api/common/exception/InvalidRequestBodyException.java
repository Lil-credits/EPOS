package io.epos.portal_api.api.common.exception;

/**
 * Exception thrown when the request body is invalid.
 * This typically corresponds to a 400 Bad Request HTTP status code.
 */
public class InvalidRequestBodyException extends BadRequestException {

  /**
   * Constructs a new InvalidRequestBodyException with a default detail message.
   */
  public InvalidRequestBodyException() {
    super("Invalid request body");
  }

  /**
   * Constructs a new InvalidRequestBodyException with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidRequestBodyException(String message) {
    super(message);
  }
}
