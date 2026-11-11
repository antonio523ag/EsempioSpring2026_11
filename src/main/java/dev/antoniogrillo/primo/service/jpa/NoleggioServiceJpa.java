package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.service.def.NoleggioService;
import dev.antoniogrillo.primo.repository.NoleggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoleggioServiceJpa implements NoleggioService {
    private final NoleggioRepository noleggioRepository;

    @Override
    public void noleggia(Noleggio noleggio) {
        noleggioRepository.save(noleggio);
    }

    @Override
    public void checkDisponibilita(long idImmobile, LocalDateTime dataInizio, LocalDateTime dataFine) {
        //controllo se l'immobile è disponibile nel periodo specificato
        //se no
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Immobile non disponibile nel periodo specificato");
    }

    @Override
    public List<Noleggio> getNoleggiByPersonaList(List<Long> idPersone) {
        return noleggioRepository.findAllByNoleggiatore_idIn(idPersone);
    }
}
