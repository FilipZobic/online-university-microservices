package rs.singidunum.userservice.exception;

public class TokenExpiredException extends Exception {
  public TokenExpiredException(String s) {
    super(s);
  }
}
