package dev.antoniogrillo.primo.facade.def;

import dev.antoniogrillo.primo.dto.noleggio.request.NoleggioRequest;
import dev.antoniogrillo.primo.entity.Persona;

public interface NoleggioControllerFacade {
    void noleggia(NoleggioRequest request, Persona utente);
}
