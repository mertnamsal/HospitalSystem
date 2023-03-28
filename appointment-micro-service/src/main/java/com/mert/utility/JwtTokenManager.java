package com.mert.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {
    private final String sifreAnahtari = "Od%43wuj5#]Q3`Ugq_|d2~uaiKPuK2M9$y2FAQv&U5*H@wL4`BKK[>@VX-#u";
    private final Long exTime = 1000*60*30L; //30 dk
    public Optional<String> createToken(Long id){
        String token = "";
        try{
            /**
             *  withClaim -> içinde key-value şeklinde bilgiler saklanır.
             *  Bu bilgiler Payload olarak tutulur ve herkes tarafından görülür.Bu nedenle buraya özel bilgiler koyulmaz.
             *  withIssuer -> jwt yi oluşturan kişinin kimliğini tutmak için kullanılır.
             *  withIssuedAt -> Jwt yi oluşturma zamanı
             *  withExpiresAt -> Jwt nin geçerlilik süresi  30 snde olabilir sonsuzda olabilir
             *  sign -> hazırlanan içeriğin imzalanması yapılır. Bunun için bir şifre belirlenir ve bununla kriptolama yapılır.
             */
            token = JWT.create().withAudience()
                    .withClaim("id",id)
                    .withClaim("howtopage","AUTHPAGE")
                    .withClaim("yetki","ADMIN")
                    .withIssuer("Java5")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exTime))
                    .sign(Algorithm.HMAC512(sifreAnahtari));
            return Optional.of(token);

        }catch (Exception exception){
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java5").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null) return false;
        }catch(Exception exception){
            return false;
        }
        return true;
    }

    public Optional<Long> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java5").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null) return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch(Exception exception){
            return Optional.empty();
        }
    }
}
