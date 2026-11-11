package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.entity.Persona;

import java.util.List;
import java.util.Map;

public interface PersonaResolverFacade {
    List<Persona> getPersone();

    boolean registraPersona(RegistraPersonaDTO input);

    Map<Persona, List<Noleggio>> getNoleggi(List<Persona> persone);
}
