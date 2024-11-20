package utils;

public class RegistrationValidateException extends Exception {

    public RegistrationValidateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Registration validate exception|" + super.getMessage();
    }
}
