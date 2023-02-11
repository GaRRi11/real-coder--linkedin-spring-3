package com.realcoders.realcoderlinkedinspring3.jwtService;

import com.realcoders.realcoderlinkedinspring3.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "6A576E5A7234743777217A25432A462D4A614E645267556B5870327335763878";

    public String extractUsername(String token){
        String decodedString = decode(token);
        int number = decodedString.indexOf("username:");
        int number2 = decodedString.indexOf(",expiresAt:");
        String username = token.substring(number + 9, number2);
        return username;
    }
    public String generateToken(User user) throws NoSuchAlgorithmException {
        String header = "{\"alg\":\"HS256\",\"typ\":\"MYTOKEN\"}.";
        String payload = "{\"sub\":\"" + user.getId() + "\",\"username\":\"" + user.getUsername()
                + "\",\"expiresAt\":\"" + new Date(System.currentTimeMillis() + 1000 * 60 * 24) + "\"}.";
        String signInKey = encyptString(header+payload+getSecretKey());
        String token = header + payload + signInKey;
        return encode(token);
    }

    public boolean isTokenValid (String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) ;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String/*Date unda ikos*/ extractExpiration(String token){
        String decodedString = decode(token);
        int number = decodedString.indexOf(",expiresAt:");
        int number2 = decodedString.indexOf("}.");
        String expiresAt = token.substring(number + 11, number2);
        return expiresAt;
    }
    public String encode(String claims){
        return Base64.getEncoder().encodeToString(claims.getBytes());
    }
    public String decode(String claims){
        byte [] decoded = Base64.getDecoder().decode(claims);
        String decodedString = new String(decoded);
        return decodedString;
    }
    public String encyptString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte [] messageDigest = md.digest(input.getBytes());
        BigInteger bigInteger = new BigInteger(1,messageDigest);
        return bigInteger.toString(16);
    }



    public Key getSecretKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
