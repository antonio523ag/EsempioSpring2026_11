package dev.antoniogrillo.primo.controller;

import dev.antoniogrillo.primo.dto.persona.request.LoginRequestDTO;
import dev.antoniogrillo.primo.dto.persona.request.RegistraPersonaDTO;
import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.PersonaControllerFacade;
import dev.antoniogrillo.primo.mapper.PersonaMapper;
import dev.antoniogrillo.primo.service.def.JWTService;
import dev.antoniogrillo.primo.service.def.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persone")
public class PersonaController {
    private final PersonaControllerFacade facade;

    @PostMapping("/trova")
    public ResponseEntity<List<PersonaDTO>> filtraPersone(@RequestBody List<RicercaPersonaRequest> request){
        return ResponseEntity.ok(facade.filtra(request));
    }

    @PostMapping("/registrati")
    public ResponseEntity<Void> registrati(@RequestBody RegistraPersonaDTO request){
        facade.registra(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDTO request){
        String s=facade.login(request);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", s).build();
    }

    @GetMapping("/WhoAmI/Step1")
    public ResponseEntity<PersonaDTO> whoAmI(@RequestHeader("Authentication") String token){
        if(token.startsWith("Bearer ")) {
            token=token.substring(7);
            return ResponseEntity.ok(facade.decodeToken(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/WhoAmI/Step2")
    public ResponseEntity<PersonaDTO> whoAmI(UsernamePasswordAuthenticationToken token){
        return ResponseEntity.ok(facade.covert(token));
    }

    @GetMapping("/WhoAmI/Step3")
    public ResponseEntity<PersonaDTO> whoAmI(@AuthenticationPrincipal Persona persona){
        return ResponseEntity.ok(facade.covert(persona));
    }

}
