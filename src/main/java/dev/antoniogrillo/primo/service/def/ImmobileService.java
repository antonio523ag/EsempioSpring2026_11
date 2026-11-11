package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.entity.Immobile;

import java.util.List;

public interface ImmobileService {
    Immobile findById(long idImmobile);

    List<Immobile> getAll();
}
