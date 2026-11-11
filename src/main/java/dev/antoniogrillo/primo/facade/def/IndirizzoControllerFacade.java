package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;

public interface IndirizzoControllerFacade {
    boolean aggiungiIndirizzo(Indirizzo i);

    IndirizzoResponse getById(long id);
}
