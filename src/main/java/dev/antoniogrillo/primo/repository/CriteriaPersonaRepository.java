package dev.antoniogrillo.primo.repository;

import dev.antoniogrillo.primo.dto.persona.request.RicercaPersonaRequest;
import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.entity.Indirizzo;
import dev.antoniogrillo.primo.entity.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CriteriaPersonaRepository {
    @PersistenceContext
    private  EntityManager manager;

    public List<Persona> getPersoneFromCampo(String campo,String valore){
        //prima prendo l'oggetto per creare il tutto
        CriteriaBuilder cb=manager.getCriteriaBuilder();
        //poi creo la classe per gestire la mia query
        CriteriaQuery<Persona> cq=cb.createQuery(Persona.class);
        //mi serve una classe che mi permetta di accedere all'elenco dei campi della classe persona
        Root<Persona> root=cq.from(Persona.class);
        //costruisco la mia condizione where
        Predicate p=cb.like(root.get(campo),"%"+valore+"%");
        Join<Persona,Indirizzo> domicilio=root.join("domiciliazioni");
        //setto la mia where alla query
        cq.where(p);
        return manager.createQuery(cq).getResultList();
    }

    public List<Persona> filtra(List<RicercaPersonaRequest> request){
        CriteriaBuilder cb=manager.getCriteriaBuilder();
        CriteriaQuery<Persona> query=cb.createQuery(Persona.class);
        Root<Persona> root=query.from(Persona.class);
        Join<Persona,Indirizzo> indirizzi=root.join("domiciliazioni");
        Join<Indirizzo, Immobile> immobili=indirizzi.join("immobili");
        Predicate p =calcolaPredicate(cb,root,indirizzi,immobili,request.get(0));
        for(int i=1;i<request.size();i++){
            Predicate p1=calcolaPredicate(cb,root,indirizzi,immobili,request.get(i));
            p=cb.and(p,p1);
        }
        query.where(p);
        return manager.createQuery(query).getResultList();
    }

    private Predicate calcolaPredicate(CriteriaBuilder cb,
                                       Root<Persona> root,
                                       Join<Persona,Indirizzo> indirizzi,
                                       Join<Indirizzo,Immobile> immobili,
                                       RicercaPersonaRequest request){
        return switch (request.getClasse().trim().toLowerCase()){
            case "persona"->cb.equal(root.get(request.getCampo()),request.getValore());
            case "indirizzo"-> cb.equal(indirizzi.get(request.getCampo()),request.getValore());
            case "immobile"->cb.equal(immobili.get(request.getCampo()),request.getValore());
            default -> null;
        };
    }
}
