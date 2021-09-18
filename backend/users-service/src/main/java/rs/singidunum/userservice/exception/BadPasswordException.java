package rs.singidunum.userservice.exception;

public class BadPasswordException extends Exception {
    public BadPasswordException() {
        super("Bad password");
    }
}
