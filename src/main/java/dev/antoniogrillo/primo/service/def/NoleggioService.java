package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.entity.Noleggio;

import java.time.LocalDateTime;
import java.util.List;

public interface NoleggioService {
    void noleggia(Noleggio noleggio);

    void checkDisponibilita(long idImmobile, LocalDateTime dataInizio, LocalDateTime dataFine);

    List<Noleggio> getNoleggiByPersonaList(List<Long> idPersone);
}
