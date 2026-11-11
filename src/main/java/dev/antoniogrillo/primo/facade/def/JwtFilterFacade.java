package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.entity.Persona;

public interface JwtFilterFacade {
    Persona decodeToken(String token);
}
