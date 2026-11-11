package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

public interface PersonaControllerFacade {
    List<PersonaDTO> filtra(List<RicercaPersonaRequest> request);

    void registra(RegistraPersonaDTO request);

    String login(LoginRequestDTO request);

    PersonaDTO decodeToken(String token);

    PersonaDTO covert(UsernamePasswordAuthenticationToken token);

    PersonaDTO covert(Persona persona);
}
