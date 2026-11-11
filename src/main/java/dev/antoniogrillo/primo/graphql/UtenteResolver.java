package dev.antoniogrillo.primo.graphql;

import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.PersonaResolverFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UtenteResolver {
    private final PersonaResolverFacade facade;

    @QueryMapping
    public List<Persona> getPersone(){
        System.out.println("sto chiamando il get persona");
        return facade.getPersone();
    }

    @SchemaMapping(typeName = "Utente", field = "eta")
    public int getEta(Persona utente){
        System.out.println("sto chiamando il get eta di "+utente.getNome());
        return (int)ChronoUnit.YEARS.between(utente.getDataNascita(), LocalDate.now());
    }

    @SchemaMapping(typeName = "Utente", field = "dataDiNascita")
    public String getDataNascita(Persona utente){
        return utente.getDataNascita().toString();
    }

    @MutationMapping
    public boolean registaUtente(@Argument("input")RegistraPersonaDTO input){
        return facade.registraPersona(input);
    }

    @BatchMapping(field = "noleggi",typeName = "Utente")
    public Map<Persona,List<Noleggio>> getNoleggi(List<Persona> persone){
        Map<Persona,List<Noleggio>> m= facade.getNoleggi(persone);
        return m;
    }
}
