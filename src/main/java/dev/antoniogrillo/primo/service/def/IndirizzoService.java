package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;

public interface IndirizzoService {

    boolean aggiungiIndirizzo(Indirizzo i);

    Indirizzo getById(long id);
}
