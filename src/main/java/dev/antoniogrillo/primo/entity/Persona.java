package dev.antoniogrillo.primo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Persona implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Il nome non può essere vuoto")
    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;
    @NotNull(message = "Il cognome non può essere vuoto")
    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;
    @NotNull(message = "La data di nascita non può essere vuota")
    @Past(message = "La data di nascita non può essere nel futuro")
    private LocalDate dataNascita;
    @NotNull(message = "L'email non può essere vuota")
    @Email(message = "L'email non è valida")
    private String email;
    @NotNull(message = "La password non può essere vuota")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La password deve contenere almeno 8 caratteri, una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale")
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
    @NotNull(message = "Il ruolo non può essere vuoto")
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
    @Transient
    private String aBitazione;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
