package io.epos.portal_api.api.common.exception;

/**
 * Exception thrown when a resource is not found.
 * This typically corresponds to a 404 Not Found HTTP status code.
 */
public class NotFoundException extends HttpRequestException {

    /**
    * Constructs a new NotFoundException with a default detail message.
    */
    public NotFoundException() {
      super("Resource not found", 404);
    }

    /**
    * Constructs a new NotFoundException with the specified detail message.
    *
    * @param message the detail message
    */
    public NotFoundException(String message) {
      super(message, 404);
    }

}
