package dev.antoniogrillo.primo.service.def;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PersonaService {
    List<Persona> filtra(List<RicercaPersonaRequest> request);

    void registra(@Valid Persona request);

    Persona login(@NotNull(message = "l'email deve essere presente")
                @Email(message = "l'email deve essere valida")
                String email,
                 @NotNull(message = "la password deve essere presente")
                 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "la password deve contenere almeno 8 caratteri, una maiuscola, una minuscola, un numero e un carattere speciale")
                 String password);

    Persona findByEmail(String email);
}
