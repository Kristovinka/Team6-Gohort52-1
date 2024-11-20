package utils;

public class RoleValidateException extends Exception {

    public RoleValidateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Role validate exception|" + super.getMessage();
    }
}
