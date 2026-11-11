package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.entity.Noleggio;

import java.time.LocalDateTime;

public interface NoleggioService {
    void noleggia(Noleggio noleggio);

    void checkDisponibilita(long idImmobile, LocalDateTime dataInizio, LocalDateTime dataFine);
}
