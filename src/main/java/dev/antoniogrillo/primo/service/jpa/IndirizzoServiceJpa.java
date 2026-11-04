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
        if(i.getCap()==null||i.getCap().length()!=5){
            throw new DatiNonValidiException("cap","il cap deve essere presente e contenere 5 caratteri");
        }
        if(i.getCitta()==null||i.getCitta().isBlank()){
            throw new DatiNonValidiException("citta","devi inserire una città per poter inserire un indirizzo");
        }
        if(i.getVia()==null||i.getVia().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"la via non può essere null");
        }
        repository.save(i);
        return true;
    }

    @Override
    public IndirizzoResponse getById(long id) {
        Indirizzo i= repository.findById(id).orElse(null);
        if(i==null)return null;
        return mapper.toIndirizzoResponse(i);
    }
}
