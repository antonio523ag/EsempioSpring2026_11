package dev.antoniogrillo.primo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "indirizzi_persone",
            joinColumns = @JoinColumn(name = "id_persona",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_indirizzo",nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(name = "indirizzo_persona_univoco",columnNames = {"id_persona","id_indirizzo"})
            }
    )
    private List<Indirizzo> domiciliazioni;

    private String aBitazione;
}
