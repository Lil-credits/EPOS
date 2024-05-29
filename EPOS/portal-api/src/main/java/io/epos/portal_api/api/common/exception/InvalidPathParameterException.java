package io.epos.portal_api.api.common.exception;

public class InvalidPathParameterException extends BadRequestException {

  public InvalidPathParameterException() {
    super("Invalid path parameter");
  }
  public InvalidPathParameterException(String message) {
    super(message);
  }
}
