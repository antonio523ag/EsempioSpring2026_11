package dev.antoniogrillo.primo.dto.persona.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegistraPersonaDTO(
        @NotNull(message = "il nome deve essere presente")
        @NotBlank(message = "il nome deve essere presente")
        String nome,
        @NotNull(message = "il cognome deve essere presente")
        @NotBlank(message = "il cognome deve essere presente")
        String cognome,
        @NotNull(message = "l'email deve essere presente")
        @Email(message = "l'email deve essere valida")
        String email,
        @NotNull(message = "la password deve essere presente")
        @Size(min = 8, message = "la password deve contenere almeno 8 caratteri")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "la password deve contenere almeno una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale")
        String password,
        @NotNull(message = "la data di nascita deve essere presente")
        @Past(message = "la data di nascita deve essere nel passato")
        LocalDate dataNascita
) {
}
