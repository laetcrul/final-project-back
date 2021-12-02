package be.digitalcity.laetitia.finalproject.exceptions.models;

public class UsernamePasswordInvalidException extends RuntimeException{
    public UsernamePasswordInvalidException() {
            super("Username/password invalid");
        }
}
