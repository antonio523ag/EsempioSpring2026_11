package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.entity.Immobile;

import java.util.List;

public interface ImmobileGraphQLFacade {
    List<Immobile> getImmobili();
}
