package utils.validator;

public class EmailValidateException extends Exception{

    public EmailValidateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Email validate exception|" + super.getMessage();
    }
}
