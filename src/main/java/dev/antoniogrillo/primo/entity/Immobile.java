package dev.antoniogrillo.primo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "edificio",uniqueConstraints = {
        @UniqueConstraint(name="metratura_e_prezzo",columnNames = {"larghezza","prezzo"})
})
public class Immobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "larghezza",nullable = false)
    private int metratura;
    private double prezzo;
    private Tipologia tipologia;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "id_indirizzo",nullable = false)
    private Indirizzo indirizzo;

}
