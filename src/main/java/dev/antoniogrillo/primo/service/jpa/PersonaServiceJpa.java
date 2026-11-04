package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.repository.PersonaRepository;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServiceJpa implements PersonaService {

    private final PersonaRepository repository;
}
