package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.mapper.PersonaMapper;
import dev.antoniogrillo.primo.repository.CriteriaPersonaRepository;
import dev.antoniogrillo.primo.repository.PersonaRepository;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceJpa implements PersonaService {

    private final CriteriaPersonaRepository repository;
    private final PersonaMapper personaMapper;

    public List<Persona> getFromNome(String nome){
        return repository.getPersoneFromCampo("nome",nome);
    }

    public List<Persona> getFromCognome(String cognome){
        return repository.getPersoneFromCampo("cognome",cognome);
    }

    @Override
    public List<PersonaDTO> filtra(List<RicercaPersonaRequest> request) {
        List<Persona> persone=repository.filtra(request);
        return personaMapper.toPersonaDTO(persone);
    }
}
