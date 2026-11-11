package dev.antoniogrillo.primo.dto.noleggio.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoleggioRequest {
    private long idImmobile;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
}
