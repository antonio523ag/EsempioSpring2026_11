package dev.antoniogrillo.primo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "noleggi",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"noleggio_id","immobile_id"},name = "unique_noleggio_immobile")
})
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "noleggio_id",nullable = false)
    private Persona noleggiatore;
    @ManyToOne
    @JoinColumn(name = "immobile_id",nullable = false)
    private Immobile immobile;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
}
