package org.panda.systems.kakeipon.domain.service.users;

public class UnavailableUserException extends RuntimeException {
  public UnavailableUserException( String message ) {
    super( message );
  }
}
