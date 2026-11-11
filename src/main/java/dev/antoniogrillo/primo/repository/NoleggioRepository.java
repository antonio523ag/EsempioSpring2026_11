package dev.antoniogrillo.primo.repository;

import dev.antoniogrillo.primo.entity.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {
}
