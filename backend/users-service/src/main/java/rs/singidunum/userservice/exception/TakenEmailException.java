package rs.singidunum.userservice.exception;

public class TakenEmailException extends Exception{
  public TakenEmailException() {
    super("Email is taken");
  }
}
