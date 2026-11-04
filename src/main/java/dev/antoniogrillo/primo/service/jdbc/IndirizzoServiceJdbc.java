package dev.antoniogrillo.primo.service.jdbc;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.service.def.IndirizzoService;
import org.springframework.stereotype.Service;

public class IndirizzoServiceJdbc implements IndirizzoService {
    @Override
    public boolean aggiungiIndirizzo(Indirizzo i) {
        return false;
    }

    @Override
    public IndirizzoResponse getById(long id) {
        return null;
    }
}
