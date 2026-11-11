package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.repository.PersonaRepository;
import dev.antoniogrillo.primo.service.def.JWTService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class JWTServiceJpa implements JWTService {

    @Value("${custom.key.jwt}")
    private String key;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public String generateToken(Persona persona) {
        JwtBuilder builder=Jwts.builder();
        JwtBuilder.BuilderClaims claims=builder.claims();
        claims.add("role",persona.getRuolo());
        claims.add("dataNascita",persona.getDataNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        claims.add("stringaACaso","ciao a tutti, sono un testo inutile");
        claims.subject(persona.getEmail());
        claims.issuedAt(new Date(System.currentTimeMillis()));
        long durataInMillisecondi=1000L*60*60*24*30;
        claims.expiration(new Date(System.currentTimeMillis()+durataInMillisecondi));
        builder=claims.and();
        return builder.signWith(getKey()).compact();
    }


    @Override
    public String decodeToken(String token) {
        Claims claims=getClaims(token);
        return claims.getSubject();

    }

    private Claims getClaims(String token){
        JwtParserBuilder builder= Jwts.parser()
                .verifyWith(getKey());
        JwtParser parser=builder.build();
        return (Claims) parser.parse(token).getPayload();
    }

}
