package io.epos.portal_api.api.common.exception;

public class InvalidRequestBodyException extends BadRequestException {

  public InvalidRequestBodyException() {
    super("Invalid request body");
  }
  public InvalidRequestBodyException(String message) {
    super(message);
  }
}
