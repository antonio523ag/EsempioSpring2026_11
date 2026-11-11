package dev.antoniogrillo.primo.controller;

import dev.antoniogrillo.primo.dto.noleggio.request.NoleggioRequest;
import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.NoleggioControllerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoleggioController {

    private final NoleggioControllerFacade facade;



    @PostMapping("/noleggia")
    public ResponseEntity<Void> noleggia(@RequestBody NoleggioRequest request, @AuthenticationPrincipal Persona utente){
        facade.noleggia(request,utente);
        return ResponseEntity.ok().build();
    }
}
