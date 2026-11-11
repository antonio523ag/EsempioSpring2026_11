package dev.antoniogrillo.primo.mapper;

import dev.antoniogrillo.primo.dto.noleggio.request.NoleggioRequest;
import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.entity.Noleggio;
import dev.antoniogrillo.primo.entity.Persona;
import org.springframework.stereotype.Component;

@Component
public class NoleggioMapper {
    public Noleggio toEntity(NoleggioRequest request, Persona p, Immobile i) {
        Noleggio n=new Noleggio();
        n.setImmobile(i);
        n.setNoleggiatore(p);
        n.setDataInizio(request.getDataInizio());
        n.setDataFine(request.getDataFine());
        return n;
    }
}
