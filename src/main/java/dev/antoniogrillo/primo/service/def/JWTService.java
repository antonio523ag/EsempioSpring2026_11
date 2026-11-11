package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.entity.Persona;

public interface JWTService {

    String generateToken(Persona persona);
    String decodeToken(String token);
}
