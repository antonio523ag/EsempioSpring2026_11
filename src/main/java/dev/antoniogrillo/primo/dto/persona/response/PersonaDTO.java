package dev.antoniogrillo.primo.dto.persona.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private String nome;
    private String cognome;
    private String email;
    private int eta;
}
