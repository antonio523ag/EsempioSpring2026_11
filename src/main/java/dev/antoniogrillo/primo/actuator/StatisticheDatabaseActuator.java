package dev.antoniogrillo.primo.actuator;

import dev.antoniogrillo.primo.repository.ImmobileRepository;
import dev.antoniogrillo.primo.repository.IndirizzoRepository;
import dev.antoniogrillo.primo.repository.NoleggioRepository;
import dev.antoniogrillo.primo.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticheDatabaseActuator implements HealthIndicator {

    private final PersonaRepository personaRepository;
    private final IndirizzoRepository indirizzoRepository;
    private final NoleggioRepository noleggioRepository;
    private final ImmobileRepository immobileRepository;

    @Override
    public @Nullable Health health() {
        long countPersone=personaRepository.count();
        long countImmobili=immobileRepository.count();
        long countNoleggi=noleggioRepository.count();
        long countIndirizzi=indirizzoRepository.count();

        return Health.up()
                .withDetail("countPersone", countPersone)
                .withDetail("countImmobili", countImmobili)
                .withDetail("countNoleggi", countNoleggi)
                .withDetail("countIndirizzi", countIndirizzi)
                .build();
    }
}
