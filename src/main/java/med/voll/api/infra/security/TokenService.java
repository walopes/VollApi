package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.user.Usuario;

@Service
public class TokenService {
    

    public String GerarToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256("senha123");
            return JWT.create()
                .withIssuer("Api Voll.med")
                .withSubject(usuario.getLogin())
                .withClaim("id", usuario.getId())
                .withExpiresAt(dateExpiration())
                .sign(algorithm);

        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("error when generating JWT", exception);
        }
    }

    private Instant dateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
