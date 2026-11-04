package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.repository.ImmobileRepository;
import dev.antoniogrillo.primo.service.def.ImmobileService;
import org.springframework.stereotype.Service;

@Service
public class ImmobileServiceJpa implements ImmobileService {

    //ConstructorInjection
    private final ImmobileRepository repository;


    public ImmobileServiceJpa(ImmobileRepository repository) {
        this.repository = repository;
    }
}
