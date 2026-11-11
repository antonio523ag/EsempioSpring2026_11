package dev.antoniogrillo.primo.service.jdbc;

import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.service.def.IndirizzoService;

public class IndirizzoServiceJdbc implements IndirizzoService {
    @Override
    public boolean aggiungiIndirizzo(Indirizzo i) {
        return false;
    }

    @Override
    public Indirizzo getById(long id) {
        return null;
    }
}
