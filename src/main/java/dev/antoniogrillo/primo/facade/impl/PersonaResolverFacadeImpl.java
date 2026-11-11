package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.PersonaResolverFacade;
import dev.antoniogrillo.primo.mapper.PersonaMapper;
import dev.antoniogrillo.primo.service.def.NoleggioService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaResolverFacadeImpl implements PersonaResolverFacade {

    private final PersonaService personaService;
    private final PersonaMapper mapper;
    private final NoleggioService noleggioService;

    @Override
    public List<Persona> getPersone() {
        return personaService.getAll();
    }

    @Override
    public boolean registraPersona( RegistraPersonaDTO input) {
        Persona persona = mapper.toPersona(input);
        personaService.registra(persona);
        return true;
    }

    @Override
    public Map<Persona, List<Noleggio>> getNoleggi(List<Persona> persone) {
        List<Long> idPersone=persone.stream().map(Persona::getId).toList();
        List<Noleggio> noleggi=noleggioService.getNoleggiByPersonaList(idPersone);
        Map<Persona,List<Noleggio>> result=new HashMap<>();
        persone.forEach(p->result.put(p,new ArrayList<>()));
        for(Noleggio n:noleggi){
            result.get(n.getNoleggiatore()).add(n);
        }
        return result;
    }
}
