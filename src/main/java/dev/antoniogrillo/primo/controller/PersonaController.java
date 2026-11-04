package dev.antoniogrillo.primo.controller;

import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.dto.persona.response.PersonaDTO;
import dev.antoniogrillo.primo.service.def.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persone")
public class PersonaController {

    private final PersonaService service;

    @PostMapping("/trova")
    public ResponseEntity<List<PersonaDTO>> filtraPersone(@RequestBody List<RicercaPersonaRequest> request){
        return ResponseEntity.ok(service.filtra(request));
    }
}
