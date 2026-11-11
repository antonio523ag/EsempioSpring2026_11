package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.repository.ImmobileRepository;
import dev.antoniogrillo.primo.service.def.ImmobileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ImmobileServiceJpa implements ImmobileService {

    //ConstructorInjection
    private final ImmobileRepository repository;


    public ImmobileServiceJpa(ImmobileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Immobile findById(long idImmobile) {
        return repository.findById(idImmobile).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Immobile non trovato"));
    }

    @Override
    public List<Immobile> getAll() {
        return repository.findAll();
    }
}
