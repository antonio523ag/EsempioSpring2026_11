package dev.antoniogrillo.primo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private String citta;
    private String cap;
    private String civico;
    @OneToMany(mappedBy = "indirizzo")
    private List<Immobile> immobili;
    @JsonIgnore
    @ManyToMany(mappedBy = "domiciliazioni",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    private List<Persona> residenti;
}
