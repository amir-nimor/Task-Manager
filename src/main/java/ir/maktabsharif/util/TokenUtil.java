package ir.maktabsharif.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {

    private TokenUtil(){}

    private static final Algorithm algorithm = Algorithm.HMAC256("my-secret-key-for-learning");

    public static String generateToken(String email){
        return JWT.create().withSubject(email).sign(algorithm);
    }

    public static String getEmailFormTokent(String token){
        return JWT.require(algorithm).build().verify(token).getSubject();
    }
}
