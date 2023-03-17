package com.realcoders.realcoderlinkedinspring3.web.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class JwtService {

    private static final String SECRET_KEY = "6A576E5A7234743777217A25432A462D4A614E645267556B5870327335763878";


    public String extractUsername(String token) throws JsonProcessingException {
        String decoedString = decode(token).get(1);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(decoedString);
        return node.get("username").asText();
    }

    public Long extractExpiration(String token) throws JsonProcessingException {
        String decodedString = decode(token).get(1);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(decodedString);
        return node.get("expiresAt").asLong();
    }

    public String generateToken(User user) throws NoSuchAlgorithmException {
        Long expiration = System.currentTimeMillis() + 86400000;
        String header = "{\"alg\":\"HS256\",\"typ\":\"MYTOKEN\"}";
        String payload = "{\"sub\":\"" + user.getId() + "\",\"username\":\"" + user.getUsername()
                + "\",\"expiresAt\":\"" + expiration +  "\"}";
        String signInKey = "{\""+encyptString(header+payload+getSecretKey())+"\"}";
        return encode(header) + "." + encode(payload) + "." + encode(signInKey);
    }

    public boolean isTokenValid (String token, User user) throws JsonProcessingException {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && TokenIsNotExpired(token);
    }

    public boolean TokenIsNotExpired(String token) throws JsonProcessingException {
        return extractExpiration(token) > System.currentTimeMillis();
    }


    public String encode(String claims)  {
        return Base64.getEncoder().withoutPadding().encodeToString(claims.getBytes(StandardCharsets.UTF_8));
    }

    public List<String> decode(String claims) {
        String [] array2 = claims.split("\\.");
        byte [] headerbyte = Base64.getDecoder().decode(array2[0]);
        byte [] payloadbyte = Base64.getDecoder().decode(array2[1]);
        byte [] signinkeybyte = Base64.getUrlDecoder().decode(array2[2]);
        String headerString = new String(headerbyte);
        String payloadString = new String(payloadbyte);
        String signInString = new String(signinkeybyte);
        return List.of(headerString, payloadString, signInString);
    }

    public String encyptString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte [] messageDigest = md.digest(input.getBytes());
        BigInteger bigInteger = new BigInteger(1,messageDigest);
        return bigInteger.toString(16);
    }

    public String getSecretKey(){
        return SECRET_KEY;
    }
}
