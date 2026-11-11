package dev.antoniogrillo.primo.facade.impl;

import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.facade.def.ImmobileGraphQLFacade;
import dev.antoniogrillo.primo.service.def.ImmobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImmobileGraphQLFacadeImpl implements ImmobileGraphQLFacade {

    private final ImmobileService service;

    @Override
    public List<Immobile> getImmobili() {
        return service.getAll();
    }
}
