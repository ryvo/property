package cz.ryvo.propertymanager.backend.exception;

public class NotFoundException extends ServerException {
  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
