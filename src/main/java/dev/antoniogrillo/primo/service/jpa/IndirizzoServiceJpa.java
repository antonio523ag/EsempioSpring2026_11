package dev.antoniogrillo.primo.service.jpa;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.mapper.IndirizzoMapper;
import dev.antoniogrillo.primo.repository.IndirizzoRepository;
import dev.antoniogrillo.primo.service.def.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoServiceJpa implements IndirizzoService {

    @Autowired //FieldInjection
    private IndirizzoRepository repository;
    @Autowired
    private IndirizzoMapper mapper;

    @Override
    public boolean aggiungiIndirizzo(Indirizzo i) {
        if(i.getCap()!=null&&i.getCitta()!=null){
            repository.save(i);
            return true;
        }
        return false;
    }

    @Override
    public IndirizzoResponse getById(long id) {
        Indirizzo i= repository.findById(id).orElse(null);
        if(i==null)return null;
        return mapper.toIndirizzoResponse(i);
    }
}
