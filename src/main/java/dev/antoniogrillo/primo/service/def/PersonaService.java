package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;

import java.util.List;

public interface PersonaService {
    List<PersonaDTO> filtra(List<RicercaPersonaRequest> request);
}
