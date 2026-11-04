package dev.antoniogrillo.primo.mapper;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndirizzoMapper {
    private final PersonaMapper personaMapper;

    public IndirizzoResponse toIndirizzoResponse(Indirizzo i){
        IndirizzoResponse i1=new IndirizzoResponse();
        i1.setAbitanti(personaMapper.toPersonaPerIndirizzoResponse(i.getResidenti()));
        i1.setCap(i.getCap());
        i1.setCitta(i.getCitta());
        i1.setCivico(i.getCivico());
        i1.setVia(i.getVia());
        return i1;
    }
}
