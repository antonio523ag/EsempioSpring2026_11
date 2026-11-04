package dev.antoniogrillo.primo.repository;

import dev.antoniogrillo.primo.entity.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImmobileRepository extends JpaRepository<Immobile,Long> {

    List<Immobile> findAllByIndirizzo_Civico(String civico);
}
