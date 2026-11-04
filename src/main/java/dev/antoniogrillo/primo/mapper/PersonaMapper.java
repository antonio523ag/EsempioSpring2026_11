package dev.antoniogrillo.primo.mapper;

import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.dto.persona.response.PersonaPerIndirizzoResponse;
import dev.antoniogrillo.primo.entity.Persona;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaMapper {

    public PersonaPerIndirizzoResponse toPersonaPerIndirizzoResponse(Persona p){
        PersonaPerIndirizzoResponse p1=new PersonaPerIndirizzoResponse();
        p1.setNome(p.getNome());
        p1.setCognome(p.getCognome());
        if(p.getDataNascita()!=null)p1.setEta((int)ChronoUnit.YEARS.between(p.getDataNascita(), LocalDate.now()));
        return p1;
    }

    public List<PersonaPerIndirizzoResponse> toPersonaPerIndirizzoResponse(List<Persona> p){
        return p==null||p.isEmpty()?new ArrayList<>():p.stream().map(this::toPersonaPerIndirizzoResponse).toList();
    }

    public PersonaDTO toPersonaDTO(Persona p){
        PersonaDTO p1=new PersonaDTO();
        p1.setNome(p.getNome());
        p1.setCognome(p.getCognome());
        p1.setEmail(p.getEmail());
        if(p.getDataNascita()!=null)p1.setEta((int)ChronoUnit.YEARS.between(p.getDataNascita(), LocalDate.now()));
        return p1;
    }

    public List<PersonaDTO> toPersonaDTO(List<Persona> p){
        return p==null||p.isEmpty()?new ArrayList<>():p.stream().map(this::toPersonaDTO).toList();
    }
}
