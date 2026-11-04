package dev.antoniogrillo.primo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cane {
    private String nome;
    private String razza;
    private LocalDate dataNascita;
    private boolean microchip;
}
