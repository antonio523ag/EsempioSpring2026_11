package dev.antoniogrillo.primo.dto.indirizzo.response;

import dev.antoniogrillo.primo.dto.persona.response.PersonaPerIndirizzoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndirizzoResponse {
    private String via;
    private String citta;
    private String cap;
    private String civico;
    private List<PersonaPerIndirizzoResponse> abitanti;
}
