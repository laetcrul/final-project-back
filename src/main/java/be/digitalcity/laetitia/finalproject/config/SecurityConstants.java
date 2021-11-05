package be.digitalcity.laetitia.finalproject.config;

public class SecurityConstants {
    public final static long EXPIRATION_TIME = 86400000; // 1 jour
    public final static String JWT_KEY = "M@_cL3f_S3cR3t3";
    public final static String TOKEN_PREFIX = "Bearer ";
    public final static String HEADER_KEY = "Authorization";
    public final static String REGISTER_URL = "/register";
    public final static String LOGIN_URL = "/login";
}
