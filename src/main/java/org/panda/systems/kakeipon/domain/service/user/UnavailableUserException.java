package org.panda.systems.kakeipon.domain.service.user;

public class UnavailableUserException extends RuntimeException {
  public UnavailableUserException( String message ) {
    super( message );
  }
}
