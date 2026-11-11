package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.PersonaControllerFacade;
import dev.antoniogrillo.primo.mapper.PersonaMapper;
import dev.antoniogrillo.primo.service.def.JWTService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaControllerFacadeImpl implements PersonaControllerFacade {

    private final PersonaService service;
    private final PersonaMapper mapper;
    private final JWTService jWTService;

    @Override
    public List<PersonaDTO> filtra(List<RicercaPersonaRequest> request) {
        List<Persona> persone=service.filtra(request);
        return mapper.toPersonaDTO(persone);
    }

    @Override
    public void registra(RegistraPersonaDTO request) {
        Persona p=mapper.toPersona(request);
        service.registra(p);
    }

    @Override
    public String login(LoginRequestDTO request) {
        Persona p=service.login(request.email(),request.password());
        return jWTService.generateToken(p);
    }

    @Override
    public PersonaDTO decodeToken(String token) {
        String email=jWTService.decodeToken(token);
        Persona p=service.findByEmail(email);
        return mapper.toPersonaDTO(p);
    }

    @Override
    public PersonaDTO covert(UsernamePasswordAuthenticationToken token) {
        Persona p=(Persona) token.getPrincipal();
        return mapper.toPersonaDTO(p);
    }

    @Override
    public PersonaDTO covert(Persona persona) {
        return mapper.toPersonaDTO(persona);
    }
}
