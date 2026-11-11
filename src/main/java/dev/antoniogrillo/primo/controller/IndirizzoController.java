package dev.antoniogrillo.primo.controller;

import dev.antoniogrillo.primo.dto.indirizzo.response.IndirizzoResponse;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.facade.def.IndirizzoControllerFacade;
import dev.antoniogrillo.primo.service.def.IndirizzoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indirizzo")
@RequiredArgsConstructor
public class IndirizzoController {

    private final IndirizzoControllerFacade facade;

    @PostMapping("/aggiungi")
    public ResponseEntity<Void> aggiungiIndirizzo(@RequestBody Indirizzo i){
        boolean b= facade.aggiungiIndirizzo(i);
        if(b)return ResponseEntity.ok().build();
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<IndirizzoResponse> getIndirizzoById(@PathVariable long id){
        IndirizzoResponse i=facade.getById(id);
        if(i==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else return ResponseEntity.ok(i);
    }
}
