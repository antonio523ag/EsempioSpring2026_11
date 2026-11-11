package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.dto.noleggio.request.NoleggioRequest;
import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.NoleggioControllerFacade;
import dev.antoniogrillo.primo.mapper.NoleggioMapper;
import dev.antoniogrillo.primo.service.def.NoleggioService;
import dev.antoniogrillo.primo.service.def.ImmobileService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoleggioControllerFacadeImpl implements NoleggioControllerFacade {

    private final PersonaService personaService;
    private final ImmobileService immobileService;
    private final NoleggioService noleggioService;
    private final NoleggioMapper noleggioMapper;

    @Override
    public void noleggia(NoleggioRequest request, Persona utente) {
        Persona p=personaService.findByEmail(utente.getEmail());
        Immobile i=immobileService.findById(request.getIdImmobile());
        noleggioService.checkDisponibilita(i.getId(),request.getDataInizio(),request.getDataFine());
        Noleggio noleggio=noleggioMapper.toEntity(request,p,i);
        noleggioService.noleggia(noleggio);
    }
}
