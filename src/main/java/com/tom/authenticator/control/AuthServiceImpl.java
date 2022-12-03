package com.tom.authenticator.control;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import com.tom.authenticator.entity.LoginRequest;
import com.tom.authenticator.entity.LoginResponse;
import com.tom.authenticator.entity.User;
import com.tom.authenticator.exception.BadCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl {

    @Autowired
    private UserRepository repository;

    @Value("${auth.key.public}")
    private String publicKey;

    @Value("${auth.key.private}")
    private String privateKey;

    public LoginResponse login(LoginRequest request) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        Optional<User> userOpt = this.repository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());
        if (userOpt.isPresent()) {
            if (encryptor.checkPassword(request.getPassword(), userOpt.get().getPassword())) {
                return LoginResponse.of(userOpt.get(), true, createLoginJWT(userOpt.get()));
            } else throw new BadCredentialsException("Wrong password");
        } else throw new BadCredentialsException("User with that username or email does not exist");
    }

    public String createLoginJWT(User user){
        String key = cleanPublicAndPrivateKey(publicKey);
        byte[] decodedKey = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(userToClaims(user))
                .signWith(
                        SignatureAlgorithm.HS256,
                        decodedKey
                ).compact();
    }

    public String decodeJWT(String jwt){
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        return header + payload;
    }

    public HashMap<String, Object> userToClaims(User user){
        return new HashMap(){{
            put("username", user.getUsername());
            put("email", user.getEmail());
            put("role", user.getRole());
        }};
    }

    public String cleanPublicAndPrivateKey(String key){
        if(!key.isEmpty()){
            key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replace(" ", "")
                    .replace("\n", "");
        }
        return key;
    }
}
