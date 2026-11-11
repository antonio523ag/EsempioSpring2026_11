package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.JwtFilterFacade;
import dev.antoniogrillo.primo.service.def.JWTService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtFilterFacadeImpl implements JwtFilterFacade {

    private final JWTService jwtService;
    private final PersonaService personaService;


    @Override
    public Persona decodeToken(String token) {
        String email = jwtService.decodeToken(token);
        return personaService.findByEmail(email);
    }
}
