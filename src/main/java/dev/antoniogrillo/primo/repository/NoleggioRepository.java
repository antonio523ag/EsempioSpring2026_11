package dev.antoniogrillo.primo.repository;

import dev.antoniogrillo.primo.entity.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    public List<Noleggio> findAllByNoleggiatore_idIn(List<Long> idPersone);
}
