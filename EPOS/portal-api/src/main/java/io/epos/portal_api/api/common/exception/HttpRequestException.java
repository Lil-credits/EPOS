package io.epos.portal_api.api.common.exception;

public abstract class HttpRequestException extends Exception {
  private final int statusCode;

  public HttpRequestException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
}
