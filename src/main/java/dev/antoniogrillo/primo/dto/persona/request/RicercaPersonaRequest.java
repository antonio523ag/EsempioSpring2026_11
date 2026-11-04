package dev.antoniogrillo.primo.dto.persona.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RicercaPersonaRequest {
    private String classe;
    private String campo;
    private String valore;
}
