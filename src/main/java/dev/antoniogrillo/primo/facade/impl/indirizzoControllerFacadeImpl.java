package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.facade.def.IndirizzoControllerFacade;
import dev.antoniogrillo.primo.mapper.IndirizzoMapper;
import dev.antoniogrillo.primo.service.def.IndirizzoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class indirizzoControllerFacadeImpl implements IndirizzoControllerFacade {

    private final IndirizzoService service;
    private final IndirizzoMapper   mapper;

    @Override
    public boolean aggiungiIndirizzo(Indirizzo i) {
        service.aggiungiIndirizzo(i);
        return true;
    }

    @Override
    public IndirizzoResponse getById(long id) {
        Indirizzo i=service.getById(id);
        return mapper.toIndirizzoResponse(i);
    }
}
