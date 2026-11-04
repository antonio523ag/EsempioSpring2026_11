package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;

public interface IndirizzoService {

    public boolean aggiungiIndirizzo(Indirizzo i);

    IndirizzoResponse getById(long id);
}
