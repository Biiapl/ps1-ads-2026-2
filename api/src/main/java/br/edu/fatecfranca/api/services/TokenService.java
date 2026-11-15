package br.edu.fatecfranca.api.services;


import java.time.Instant;
import java.time.temporal.ChronoUnit;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import br.edu.fatecfranca.api.entities.User;


@Service
public class TokenService {


   private final Algorithm algorithm;


   public TokenService(@Value("${jwt.secret}") String secret) {
       this.algorithm = Algorithm.HMAC256(secret);
   }


   public String generate(User user) {
       Instant now = Instant.now();


       return JWT.create()
               .withIssuer("fatecfranca-api")
               .withSubject(user.getId().toString())
               .withIssuedAt(now)
               .withExpiresAt(now.plus(24, ChronoUnit.HOURS))
               .sign(algorithm);
   }
}

