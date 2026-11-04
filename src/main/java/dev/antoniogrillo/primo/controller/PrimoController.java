package dev.antoniogrillo.primo.controller;

import dev.antoniogrillo.primo.model.Cane;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class PrimoController {

    @RequestMapping(value = "/saluta", method = RequestMethod.GET)
    public String saluta(){
        return "ciao a tutti";
    }

    @GetMapping("/saluta2")
    public String saluta2(){
        return "ciao a tutti";
    }

    @GetMapping("/saluta/{val1}/ciao")
    public String saluta(@PathVariable("val1") String nome){
        return "ciao "+nome;
    }

    @GetMapping("/saluta3")
    public String saluta2(@RequestParam("val1") String nome){
        return "ciao "+nome;
    }

    @GetMapping("/cane")
    public Cane getCane(){
        return new Cane("Fido","Alano", LocalDate.of(2024,8,10),true);
    }

    @PostMapping("/cane")
    public void stampaCane(@RequestBody Cane c){
        System.out.println(c.getNome()+" "+c.getRazza());
    }
}
