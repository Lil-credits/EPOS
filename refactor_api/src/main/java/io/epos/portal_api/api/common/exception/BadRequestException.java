package io.epos.portal_api.api.common.exception;

  public class BadRequestException extends HttpRequestException {
    public BadRequestException(String message) {
      super(message, 400);
    }
  }

