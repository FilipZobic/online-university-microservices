package rs.singidunum.userservice.exception;

public class TakenUsernameException extends Exception {
  public TakenUsernameException() {
    super("Username is taken");
  }
}
