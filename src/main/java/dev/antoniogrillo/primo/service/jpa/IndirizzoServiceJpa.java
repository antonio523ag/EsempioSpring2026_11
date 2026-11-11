package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.exception.DatiNonValidiException;
import dev.antoniogrillo.primo.mapper.IndirizzoMapper;
import dev.antoniogrillo.primo.repository.IndirizzoRepository;
import dev.antoniogrillo.primo.service.def.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IndirizzoServiceJpa implements IndirizzoService {

    @Autowired //FieldInjection
    private IndirizzoRepository repository;
    @Autowired
    private IndirizzoMapper mapper;

    @Override
    public boolean aggiungiIndirizzo(Indirizzo i) {
        repository.save(i);
        return true;
    }

    @Override
    public Indirizzo getById(long id) {
        return repository.findById(id).orElse(null);
    }
}
