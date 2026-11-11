package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.mapper.PersonaMapper;
import dev.antoniogrillo.primo.repository.CriteriaPersonaRepository;
import dev.antoniogrillo.primo.repository.PersonaRepository;
import dev.antoniogrillo.primo.service.def.JWTService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceJpa implements PersonaService {

    private final CriteriaPersonaRepository repository;
    private final PersonaRepository personaRepository;

    public List<Persona> getFromNome(String nome){
        return repository.getPersoneFromCampo("nome",nome);
    }

    public List<Persona> getFromCognome(String cognome){
        return repository.getPersoneFromCampo("cognome",cognome);
    }

    @Override
    public List<Persona> filtra(List<RicercaPersonaRequest> request) {
        return repository.filtra(request);
    }

    @Override
    public void registra(Persona request) {
        personaRepository.save(request);
    }

    @Override
    public Persona login(String email,String password) {
        return personaRepository.findByEmailAndPassword(email,password).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Override
    public Persona findByEmail(String email) {
        return personaRepository.findByEmail(email).orElse(null);
    }
}
