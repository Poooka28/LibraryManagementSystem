package user;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String messString) {
        super(messString);
    }
}